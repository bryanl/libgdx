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
package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.ETC1TextureData;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.utils.Array;

public class TextureLoader extends AsynchronousAssetLoader<Texture, TextureLoader.TextureParameter> {
	TextureData data;
	Texture texture;

	public TextureLoader (FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public void loadAsync (AssetManager manager, String fileName, TextureParameter parameter) {
		if(parameter == null || (parameter != null && parameter.textureData == null)) {
			Pixmap pixmap = null;
			Format format = null;
			boolean genMipMaps = false;
			texture = null;

			if (parameter != null) {
				format = parameter.format;
				genMipMaps = parameter.genMipMaps;
				texture = parameter.texture;
			}
			
			FileHandle handle = resolve(fileName);
			if(!fileName.contains(".etc1")) {
				pixmap = new Pixmap(handle);
				data = new FileTextureData(handle, pixmap, format, genMipMaps);
			} else {
				data = new ETC1TextureData(handle, genMipMaps);
			}
		} else {
			data = parameter.textureData;
			texture = parameter.texture;
		}
	}

	@Override
	public Texture loadSync (AssetManager manager, String fileName, TextureParameter parameter) {
		if (texture != null) {
			texture.load(data);
			return texture;
		} else {
			return new Texture(data);
		}
	}

	@Override
	public Array<AssetDescriptor> getDependencies (String fileName, TextureParameter parameter) {
		return null;
	}

	static public class TextureParameter extends AssetLoaderParameters<Texture> {
		/** the format of the final Texture. Uses the source images format if null **/
		public Format format = null;
		/** whether to generate mipmaps **/
		public boolean genMipMaps = false;
		/** The texture to put the {@link TextureData} in, optional. **/
		public Texture texture = null;
		/** TextureData for textures created on the fly, optional. When set, all format and genMipMaps are ignored */
		public TextureData textureData = null;
	}
}