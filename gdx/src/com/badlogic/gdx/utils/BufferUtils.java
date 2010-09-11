package com.badlogic.gdx.utils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import com.badlogic.gdx.Version;

/**
 * Class with static helper methods to increase the speed of 
 * array/direct buffer and direct buffer/direct buffer transfers
 * 
 * @author mzechner
 *
 */
public class BufferUtils 
{
	static
	{
		System.loadLibrary( "gdx-" + Version.VERSION );
	}
	
	/**
	 * Copies numFloats floats from src starting at offset to dst. Dst is 
	 * assumed to be a direct {@link Buffer}. The method will crash if that
	 * is not the case. The position and limit of the buffer are ignored, the copy
	 * is placed at position 0 in the buffer. After the copying process the position
	 * of the buffer is set to 0 and its limit is set to numFloats * 4 if it is a
	 * ByteBuffer and numFloats if it is a FloatBuffer. In case the Buffer is neither 
	 * a ByteBuffer nor a FloatBuffer the limit is not set. This is an expert method,
	 * use at your own risk.  
	 * 
	 * @param src the source array
	 * @param dst the destination buffer, has to be a direct Buffer
	 * @param numFloats the number of floats to copy
	 * @param offset the offset in src to start copying from
	 */
	public static void copy( float[] src, Buffer dst, int numFloats, int offset )
	{		
		copyJni( src, dst, numFloats, offset );
		dst.position(0);
	
		if( dst instanceof ByteBuffer )
			dst.limit(numFloats << 2);
		else
		if( dst instanceof FloatBuffer )
			dst.limit(numFloats);		
	}
	
	private native static void copyJni( float[] src, Buffer dst, int numFloats, int offset );	
	
	private native static void copyJni( Buffer src, Buffer dst, int numBytes );
}
