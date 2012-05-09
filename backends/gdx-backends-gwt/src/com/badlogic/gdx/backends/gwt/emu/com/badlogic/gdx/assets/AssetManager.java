/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.assets;

import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.PixmapLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;

public class AssetManager implements Disposable {
	final ObjectMap<Class, ObjectMap<String, RefCountedContainer>> assets = new ObjectMap<Class, ObjectMap<String, RefCountedContainer>>();
	final ObjectMap<String, Class> assetTypes = new ObjectMap<String, Class>();
	final ObjectMap<String, Array<String>> assetDependencies = new ObjectMap<String, Array<String>>();

	final ObjectMap<Class, AssetLoader> loaders = new ObjectMap<Class, AssetLoader>();
	final ArrayList<AssetDescriptor> loadQueue = new ArrayList<AssetDescriptor>();

	Stack<AssetLoadingTask> tasks = new Stack<AssetLoadingTask>();
	AssetErrorListener listener = null;
	int loaded = 0;
	int toLoad = 0;

	/** Creates a new AssetManager with all default loaders. */
	public AssetManager () {
		this(new InternalFileHandleResolver());
	}

	/** Creates a new AssetManager with all default loaders. */
	public AssetManager (FileHandleResolver resolver) {
		setLoader(BitmapFont.class, new BitmapFontLoader(resolver));
		setLoader(Music.class, new MusicLoader(resolver));
		setLoader(Pixmap.class, new PixmapLoader(resolver));
		setLoader(Sound.class, new SoundLoader(resolver));
		setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
		setLoader(Texture.class, new TextureLoader(resolver));
		setLoader(Skin.class, new SkinLoader(resolver));
//		setLoader(TileMapRenderer.class, new TileMapRendererLoader(resolver));
	}

	/** @param fileName the asset file name
	 * @param type the asset type
	 * @return the asset */
	public synchronized <T> T get (String fileName, Class<T> type) {
		ObjectMap<String, RefCountedContainer> assetsByType = assets.get(type);
		if (assetsByType == null) throw new GdxRuntimeException("Asset not loaded: " + fileName);
		RefCountedContainer assetContainer = assetsByType.get(fileName);
		if (assetContainer == null) throw new GdxRuntimeException("Asset not loaded: " + fileName);
		T asset = assetContainer.getObject(type);
		if (asset == null) throw new GdxRuntimeException("Asset not loaded: " + fileName);
		return asset;
	}

	/** Removes the asset and all its dependencies if they are not used by other assets.
	 * @param fileName the file name */
	public synchronized void unload (String fileName) {
		// check if it's in the queue
		int foundIndex = -1;
		for (int i = 0; i < loadQueue.size(); i++) {
			if (loadQueue.get(i).fileName.equals(fileName)) {
				foundIndex = i;
				break;
			}
		}
		if (foundIndex != -1) {
			loadQueue.remove(foundIndex);
//			log.debug("Unload (from queue): " + fileName);
			return;
		}

		// check if it's currently processed (and the first element in the stack, thus not a dependency)
		// and cancel if necessary
		if (tasks.size() > 0) {
			AssetLoadingTask currAsset = tasks.firstElement();
			if (currAsset.assetDesc.fileName.equals(fileName)) {
				currAsset.cancel = true;
//				log.debug("Unload (from tasks): " + fileName);
				return;
			}
		}

		// get the asset and its type
		Class type = assetTypes.get(fileName);
		if (type == null) throw new GdxRuntimeException("Asset not loaded: " + fileName);

		RefCountedContainer assetRef = assets.get(type).get(fileName);

		// if it is reference counted, decrement ref count and check if we can really get rid of it.
		assetRef.decRefCount();
		if (assetRef.getRefCount() <= 0) {
//			log.debug("Unload (dispose): " + fileName);

			// if it is disposable dispose it
			if (assetRef.getObject(Object.class) instanceof Disposable) ((Disposable)assetRef.getObject(Object.class)).dispose();

			// remove the asset from the manager.
			assetTypes.remove(fileName);
			assets.get(type).remove(fileName);
		} else {
//			log.debug("Unload (decrement): " + fileName);
		}

		// remove any dependencies (or just decrement their ref count).
		Array<String> dependencies = assetDependencies.get(fileName);
		if (dependencies != null) {
			for (String dependency : dependencies) {
				unload(dependency);
			}
		}
		// remove dependencies if ref count < 0
		if (assetRef.getRefCount() <= 0) {
			assetDependencies.remove(fileName);
		}
	}

	/** @param asset the asset
	 * @return whether the asset is contained in this manager */
	public synchronized <T> boolean containsAsset (T asset) {
		ObjectMap<String, RefCountedContainer> typedAssets = assets.get(asset.getClass());
		if(typedAssets == null) return false;
		for (String fileName : typedAssets.keys()) {
			T otherAsset = (T)typedAssets.get(fileName).getObject(Object.class);
			if (otherAsset == asset || asset.equals(otherAsset)) return true;
		}
		return false;
	}

	/** @param asset the asset
	 * @return whether the filename of the asset or null */
	public synchronized <T> String getAssetFileName (T asset) {
		for (Class assetType : assets.keys()) {
			ObjectMap<String, RefCountedContainer> typedAssets = assets.get(assetType);
			for (String fileName : typedAssets.keys()) {
				T otherAsset = (T)typedAssets.get(fileName).getObject(Object.class);
				if (otherAsset == asset || asset.equals(otherAsset)) return fileName;
			}
		}
		return null;
	}

	/** @param fileName the file name of the asset
	 * @return whether the asset is loaded */
	public synchronized boolean isLoaded (String fileName) {
		if (fileName == null) return false;
		return assetTypes.containsKey(fileName);
	}

	/** @param fileName the file name of the asset
	 * @return whether the asset is loaded */
	public synchronized boolean isLoaded (String fileName, Class type) {
		ObjectMap<String, RefCountedContainer> assetsByType = assets.get(type);
		if (assetsByType == null) return false;
		RefCountedContainer assetContainer = assetsByType.get(fileName);
		if (assetContainer == null) return false;
		return assetContainer.getObject(type) != null;
	}

	/** Adds the given asset to the loading queue of the AssetManager.
	 * @param fileName the file name (interpretation depends on {@link AssetLoader})
	 * @param type the type of the asset. */
	public synchronized <T> void load (String fileName, Class<T> type) {
		load(fileName, type, null);
	}

	/** Adds the given asset to the loading queue of the AssetManager.
	 * @param fileName the file name (interpretation depends on {@link AssetLoader})
	 * @param type the type of the asset.
	 * @param parameter parameters for the AssetLoader. */
	public synchronized <T> void load (String fileName, Class<T> type, AssetLoaderParameters<T> parameter) {
		AssetLoader loader = loaders.get(type);
		if (loader == null) throw new GdxRuntimeException("No loader for type: " + type.getName());

		if (loadQueue.size() == 0) {
			loaded = 0;
			toLoad = 0;
		}

		// check if an asset with the same name but a different type has already been added.

		// check preload queue
		for (int i = 0; i < loadQueue.size(); i++) {
			AssetDescriptor desc = loadQueue.get(i);
			if (desc.fileName.equals(fileName) && !desc.type.equals(type))
				throw new GdxRuntimeException("Asset with name '" + fileName
					+ "' already in preload queue, but has different type (expected: " + type.getName() + ", found: "
					+ desc.type.getName());
		}

		// check task list
		for (int i = 0; i < tasks.size(); i++) {
			AssetDescriptor desc = tasks.get(i).assetDesc;
			if (desc.fileName.equals(fileName) && !desc.type.equals(type))
				throw new GdxRuntimeException("Asset with name '" + fileName
					+ "' already in task list, but has different type (expected: " + type.getName() + ", found: "
					+ desc.type.getName());
		}

		// check loaded assets
		Class otherType = assetTypes.get(fileName);
		if (otherType != null && !otherType.equals(type))
			throw new GdxRuntimeException("Asset with name '" + fileName + "' already loaded, but has different type (expected: "
				+ type.getName() + ", found: " + otherType.getName());

		toLoad++;
		AssetDescriptor assetDesc = new AssetDescriptor(fileName, type, parameter);
		loadQueue.add(assetDesc);
//		log.debug("Queued: " + assetDesc);
	}

	/** Adds the given asset to the loading queue of the AssetManager.
	 * @param desc the {@link AssetDescriptor} */
	public synchronized void load (AssetDescriptor desc) {
		load(desc.fileName, desc.type, desc.params);
	}

	/** Disposes the given asset and all its dependencies recursively, depth first.
	 * @param fileName */
	private void disposeDependencies (String fileName) {
		Array<String> dependencies = assetDependencies.get(fileName);
		if (dependencies != null) {
			for (String dependency : dependencies) {
				disposeDependencies(dependency);
			}
		}

		Class type = assetTypes.get(fileName);
		Object asset = assets.get(type).get(fileName).getObject(Object.class);
		if (asset instanceof Disposable) ((Disposable)asset).dispose();
	}

	/** Updates the AssetManager, keeping it loading any assets in the preload queue.
	 * @return true if all loading is finished. */
	public synchronized boolean update () {
		try {
			if (tasks.size() == 0) {
				// loop until we have a new task ready to be processed
				while (loadQueue.size() != 0 && tasks.size() == 0) {
					nextTask();
				}
				// have we not found a task? We are done!
				if (tasks.size() == 0) return true;
			}
			return updateTask() && loadQueue.size() == 0 && tasks.size() == 0;
		} catch (Throwable t) {
			handleTaskError(t);
			return loadQueue.size() == 0;
		}
	}

//	public void finishLoading () {
//		log.debug("Waiting for loading to complete...");
//		while (!update())
//			Thread.yield();
//		log.debug("Loading complete.");
//	}

	synchronized void injectDependency (String parentAssetFilename, AssetDescriptor dependendAssetDesc) {
		// add the asset as a dependency of the parent asset
		Array<String> dependencies = assetDependencies.get(parentAssetFilename);
		if (dependencies == null) {
			dependencies = new Array<String>();
			assetDependencies.put(parentAssetFilename, dependencies);
		}
		dependencies.add(dependendAssetDesc.fileName);

		// if the asset is already loaded, increase its reference count.
		if (isLoaded(dependendAssetDesc.fileName)) {
//			log.debug("Dependency already loaded: " + dependendAssetDesc);
			Class type = assetTypes.get(dependendAssetDesc.fileName);
			RefCountedContainer assetRef = assets.get(type).get(dependendAssetDesc.fileName);
			assetRef.incRefCount();
			incrementRefCountedDependencies(dependendAssetDesc.fileName);
		}
		// else add a new task for the asset.
		else {
//			log.info("Loading dependency: " + dependendAssetDesc);
			addTask(dependendAssetDesc);
		}
	}

	/** Removes a task from the loadQueue and adds it to the task stack. If the asset is already loaded (which can happen if it was
	 * a dependency of a previously loaded asset) its reference count will be increased. */
	private void nextTask () {
		AssetDescriptor assetDesc = loadQueue.remove(0);

		// if the asset not meant to be reloaded and is already loaded, increase its reference count
		if (isLoaded(assetDesc.fileName)) {
//			log.debug("Already loaded: " + assetDesc);
			Class type = assetTypes.get(assetDesc.fileName);
			RefCountedContainer assetRef = assets.get(type).get(assetDesc.fileName);
			assetRef.incRefCount();
			incrementRefCountedDependencies(assetDesc.fileName);
			loaded++;
		} else {
			// else add a new task for the asset.
//			log.info("Loading: " + assetDesc);
			addTask(assetDesc);
		}
	}

	/** Adds a {@link AssetLoadingTask} to the task stack for the given asset.
	 * @param assetDesc */
	private void addTask (AssetDescriptor assetDesc) {
		AssetLoader loader = loaders.get(assetDesc.type);
		if (loader == null) throw new GdxRuntimeException("No loader for type: " + assetDesc.type.getName());
		tasks.push(new AssetLoadingTask(this, assetDesc, loader));
	}

	/** Updates the current task on the top of the task stack.
	 * @return true if the asset is loaded. */
	private boolean updateTask () {
		AssetLoadingTask task = tasks.peek();
		// if the task has finished loading
		if (task.update()) {
			// add the asset to the filename lookup
			assetTypes.put(task.assetDesc.fileName, task.assetDesc.type);

			// add the asset to the type lookup
			ObjectMap<String, RefCountedContainer> typeToAssets = assets.get(task.assetDesc.type);
			if (typeToAssets == null) {
				typeToAssets = new ObjectMap<String, RefCountedContainer>();
				assets.put(task.assetDesc.type, typeToAssets);
			}
			typeToAssets.put(task.assetDesc.fileName, new RefCountedContainer(task.getAsset()));

			// increase the number of loaded assets and pop the task from the stack
			if (tasks.size() == 1) loaded++;
			tasks.pop();

			// remove the asset if it was canceled.
			if (task.cancel) {
				unload(task.assetDesc.fileName);
			} else {
				// otherwise, if a listener was found in the parameter invoke it
				if (task.assetDesc.params != null && task.assetDesc.params.loadedCallback != null) {
					task.assetDesc.params.loadedCallback.finishedLoading(this, task.assetDesc.fileName, task.assetDesc.type);
				}

//				long endTime = System.nanoTime();
//				log.debug("Loaded: " + (endTime - task.startTime) / 1000000f + "ms " + task.assetDesc);
			}

			return true;
		} else {
			return false;
		}
	}

	private void incrementRefCountedDependencies (String parent) {
		Array<String> dependencies = assetDependencies.get(parent);
		if (dependencies == null) return;

		for (String dependency : dependencies) {
			Class type = assetTypes.get(dependency);
			RefCountedContainer assetRef = assets.get(type).get(dependency);
			assetRef.incRefCount();
			incrementRefCountedDependencies(dependency);
		}
	}

	/** Handles a runtime/loading error in {@link #update()} by optionally invoking the {@link AssetErrorListener}.
	 * @param t */
	private void handleTaskError (Throwable t) {
//		log.error("Error loading asset.", t);

		if (tasks.isEmpty()) throw new GdxRuntimeException(t);

		// pop the faulty task from the stack
		AssetLoadingTask task = tasks.pop();
		AssetDescriptor assetDesc = task.assetDesc;

		// remove all dependencies
		if (task.dependenciesLoaded && task.dependencies != null) {
			for (AssetDescriptor desc : task.dependencies) {
				unload(desc.fileName);
			}
		}

		// clear the rest of the stack
		tasks.clear();

		// inform the listener that something bad happened
		if (listener != null) {
			listener.error(assetDesc.fileName, assetDesc.type, t);
		} else {
			throw new GdxRuntimeException(t);
		}
	}

	/** Sets a new {@link AssetLoader} for the given type.
	 * @param type the type of the asset
	 * @param loader the loader */
	public synchronized <T, P extends AssetLoaderParameters<T>> void setLoader (Class<T> type, AssetLoader<T, P> loader) {
		if (type == null) throw new IllegalArgumentException("type cannot be null.");
		if (loader == null) throw new IllegalArgumentException("loader cannot be null.");
//		log.debug("Loader set: " + type.getName() + " -> " + loader.getClass().getName());
		loaders.put(type, loader);
	}

	/** @return the number of loaded assets */
	public synchronized int getLoadedAssets () {
		return assetTypes.size;
	}

	/** @return the number of currently queued assets */
	public synchronized int getQueuedAssets () {
		return loadQueue.size() + (tasks.size());
	}

	/** @return the progress in percent of completion. */
	public synchronized float getProgress () {
		if (toLoad == 0) return 1;
		return Math.min(1, loaded / (float)toLoad);
	}

	/** Sets an {@link AssetErrorListener} to be invoked in case loading an asset failed.
	 * @param listener the listener or null */
	public synchronized void setErrorListener (AssetErrorListener listener) {
		this.listener = listener;
	}

	/** Disposes all assets in the manager and stops all asynchronous loading. */
	public synchronized void dispose () {
//		log.debug("Disposing.");
		clear();
	}

	/** Clears and disposes all assets and the preloading queue. */
	public synchronized void clear () {
		loadQueue.clear();
		while (!update())
			;

		ObjectIntMap<String> dependencyCount = new ObjectIntMap<String>();
		while (assetTypes.size > 0) {
			// for each asset, figure out how often it was referenced
			dependencyCount.clear();
			Array<String> assets = assetTypes.keys().toArray();
			for (String asset : assets) {
				dependencyCount.put(asset, 0);
			}

			for (String asset : assets) {
				Array<String> dependencies = assetDependencies.get(asset);
				if (dependencies == null) continue;
				for (String dependency : dependencies) {
					int count = dependencyCount.get(dependency, 0);
					count++;
					dependencyCount.put(dependency, count);
				}
			}

			// only dispose of assets that are root assets (not referenced)
			for (String asset : assets) {
				if (dependencyCount.get(asset, 0) == 0) {
					unload(asset);
				}
			}
		}

		this.assets.clear();
		this.assetTypes.clear();
		this.assetDependencies.clear();
		this.loaded = 0;
		this.toLoad = 0;
		this.loadQueue.clear();
		this.tasks.clear();
	}

	/** @return the {@link Logger} used by the {@link AssetManager} */
//	public Logger getLogger () {
//		return log;
//		return null;
//	}

	/** Returns the reference count of an asset.
	 * @param fileName */
	public synchronized int getReferenceCount (String fileName) {
		Class type = assetTypes.get(fileName);
		if (type == null) throw new GdxRuntimeException("Asset not loaded: " + fileName);
		return assets.get(type).get(fileName).getRefCount();
	}

	/** Sets the reference count of an asset.
	 * @param fileName */
	public synchronized void setReferenceCount (String fileName, int refCount) {
		Class type = assetTypes.get(fileName);
		if (type == null) throw new GdxRuntimeException("Asset not loaded: " + fileName);
		assets.get(type).get(fileName).setRefCount(refCount);
	}

	/** @return a string containg ref count and dependency information for all assets. */
	public synchronized String getDiagnostics () {
		StringBuffer buffer = new StringBuffer();
		for (String fileName : assetTypes.keys()) {
			buffer.append(fileName);
			buffer.append(", ");

			Class type = assetTypes.get(fileName);
			RefCountedContainer assetRef = assets.get(type).get(fileName);
			Array<String> dependencies = assetDependencies.get(fileName);

			buffer.append(type.getName());

			buffer.append(", refs: ");
			buffer.append(assetRef.getRefCount());

			if (dependencies != null) {
				buffer.append(", deps: [");
				for (String dep : dependencies) {
					buffer.append(dep);
					buffer.append(",");
				}
				buffer.append("]");
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}
	
	/**
	 * blocks until all assets are loaded.
	 */
	public void finishLoading () {
		while (!update());			
	}
}
