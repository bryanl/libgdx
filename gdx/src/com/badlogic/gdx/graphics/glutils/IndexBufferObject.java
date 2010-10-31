package com.badlogic.gdx.graphics.glutils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.BufferUtils;

/**
 * <p>
 * In IndexBufferObject wraps OpenGL's index buffer functionality to be used in
 * conjunction with VBOs. This class can be seamlessly used with OpenGL ES 1.x
 * and 2.0.
 * </p>
 * 
 * <p>
 * Uses indirect Buffers on Android 1.5/1.6 to fix GC invocation due to leaking
 * PlatformAddress instances.
 * </p>
 * 
 * <p>
 * You can also use this to store indices for vertex arrays. Do not call
 * {@link #bind()} or {@link #unbind()} in this case but rather use
 * {@link #getBuffer()} to use the buffer directly with glDrawElements. You must
 * also create the IndexBufferObject with the second constructor and specify
 * isDirect as true as glDrawElements in conjunction with vertex arrays needs
 * direct buffers.
 * </p>
 * 
 * <p>
 * VertexBufferObjects must be disposed via the {@link #dispose()} method when
 * no longer needed
 * </p>
 * 
 * @author mzechner
 * 
 */
public class IndexBufferObject {
	final static IntBuffer tmpHandle = BufferUtils.newIntBuffer(1);

	ShortBuffer buffer;
	ByteBuffer byteBuffer;
	int bufferHandle;
	final boolean isDirect;
	boolean isDirty = true;
	boolean isBound = false;
	final int usage;

	/**
	 * Creates a new IndexBufferObject.
	 * 
	 * @param isStatic
	 *            whether the index buffer is static
	 * @param maxIndices
	 *            the maximum number of indices this buffer can hold
	 */
	public IndexBufferObject(boolean isStatic, int maxIndices) {
		if (Gdx.app.getType() == ApplicationType.Android
				&& Gdx.app.getVersion() < 5) {
			byteBuffer = ByteBuffer.allocate(maxIndices * 2);
			byteBuffer.order(ByteOrder.nativeOrder());
			isDirect = false;
		} else {
			byteBuffer = ByteBuffer.allocateDirect(maxIndices * 2);
			byteBuffer.order(ByteOrder.nativeOrder());
			isDirect = true;
		}
		buffer = byteBuffer.asShortBuffer();
		bufferHandle = createBufferObject();
		usage = isStatic ? GL11.GL_STATIC_DRAW : GL11.GL_DYNAMIC_DRAW;
	}

	/**
	 * Creates a new IndexBufferObject to be used with vertex arrays.
	 * 
	 * @param maxIndices
	 *            the maximum number of indices this buffer can hold
	 */
	public IndexBufferObject(int maxIndices) {		
		byteBuffer = ByteBuffer.allocateDirect(maxIndices * 2);
		byteBuffer.order(ByteOrder.nativeOrder());
		this.isDirect = true;
		
		buffer = byteBuffer.asShortBuffer();
		bufferHandle = createBufferObject();
		usage = GL11.GL_STATIC_DRAW;
	}

	private int createBufferObject() {
		if (Gdx.gl20 != null) {
			Gdx.gl20.glGenBuffers(1, tmpHandle);
			return tmpHandle.get(0);
		} else if (Gdx.gl11 != null) {
			Gdx.gl11.glGenBuffers(1, tmpHandle);
			return tmpHandle.get(0);
		}

		return 0;
	}

	/**
	 * @return the number of indices currently stored in this buffer
	 */
	public int getNumIndices() {
		return buffer.limit();
	}

	/**
	 * @return the maximum number of indices this IndexBufferObject can store.
	 */
	public int getNumMaxIndices() {
		return buffer.capacity();
	}

	/**
	 * <p>
	 * Sets the indices of this IndexBufferObject, discarding the old indices.
	 * The count must equal the number of indices to be copied to this
	 * IndexBufferObject.
	 * </p>
	 * 
	 * <p>
	 * This can be called in between calls to {@link #bind()} and
	 * {@link #unbind()}. The index data will be updated instantly.
	 * </p>
	 * 
	 * @param indices
	 *            the vertex data
	 * @param offset
	 *            the offset to start copying the data from
	 * @param count
	 *            the number of floats to copy
	 */
	public void setIndices(short[] indices, int offset, int count) {
		isDirty = true;
		buffer.clear();
		buffer.put(indices);
		buffer.flip();
		byteBuffer.position(0);
		byteBuffer.limit(count << 1);

		if (isBound) {
			if (Gdx.gl11 != null) {
				GL11 gl = Gdx.gl11;
				gl.glBufferData(GL11.GL_ELEMENT_ARRAY_BUFFER, byteBuffer
						.limit(), null, usage);
				gl.glBufferData(GL11.GL_ELEMENT_ARRAY_BUFFER, byteBuffer
						.limit(), byteBuffer, usage);
			} else if (Gdx.gl11 != null) {
				GL20 gl = Gdx.gl20;
				gl.glBufferData(GL20.GL_ELEMENT_ARRAY_BUFFER, byteBuffer
						.limit(), null, usage);
				gl.glBufferData(GL20.GL_ELEMENT_ARRAY_BUFFER, byteBuffer
						.limit(), byteBuffer, usage);
			}
			isDirty = false;
		}
	}

	/**
	 * <p>
	 * Returns the underlying ShortBuffer. If you modify the buffer contents
	 * they wil be uploaded on the call to {@link #bind()}. If you need
	 * immediate uploading use {@link #setIndices(short[], int, int)}.
	 * </p>
	 * 
	 * @return the underlying short buffer.
	 */
	public ShortBuffer getBuffer() {
		isDirty = true;
		return buffer;
	}

	/**
	 * Binds this IndexBufferObject for rendering with glDrawElements.
	 */
	public void bind() {
		if (Gdx.gl11 != null) {
			GL11 gl = Gdx.gl11;
			gl.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER, bufferHandle);
			if (isDirty) {
				gl.glBufferData(GL11.GL_ELEMENT_ARRAY_BUFFER, byteBuffer
						.limit(), null, usage);
				gl.glBufferData(GL11.GL_ELEMENT_ARRAY_BUFFER, byteBuffer
						.limit(), byteBuffer, usage);
				isDirty = false;
			}
		} else {
			GL20 gl = Gdx.gl20;
			gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, bufferHandle);
			if (isDirty) {
				gl.glBufferData(GL20.GL_ELEMENT_ARRAY_BUFFER, byteBuffer
						.limit(), null, usage);
				gl.glBufferData(GL20.GL_ELEMENT_ARRAY_BUFFER, byteBuffer
						.limit(), byteBuffer, usage);
				isDirty = false;
			}
		}
		isBound = true;
	}

	/**
	 * Unbinds this IndexBufferObject.
	 */
	public void unbind() {
		if (Gdx.gl11 != null) {
			Gdx.gl11.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER, 0);
		} else if (Gdx.gl20 != null) {
			Gdx.gl20.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER, 0);
		}
		isBound = false;
	}

	/**
	 * Invalidates the IndexBufferObject so a new OpenGL buffer handle is
	 * created. Use this in case of a context loss.
	 */
	public void invalidate() {
		bufferHandle = createBufferObject();
		isDirty = true;
	}

	/**
	 * Disposes this IndexBufferObject and all its associated OpenGL resources.
	 */
	public void dispose() {
		if (Gdx.gl20 != null) {
			tmpHandle.clear();
			tmpHandle.put(bufferHandle);
			tmpHandle.flip();
			GL20 gl = Gdx.gl20;
			gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, 0);
			gl.glDeleteBuffers(1, tmpHandle);
			bufferHandle = 0;
		} else if (Gdx.gl11 != null) {
			tmpHandle.clear();
			tmpHandle.put(bufferHandle);
			tmpHandle.flip();
			GL11 gl = Gdx.gl11;
			gl.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER, 0);
			gl.glDeleteBuffers(1, tmpHandle);
			bufferHandle = 0;
		}
	}
}
