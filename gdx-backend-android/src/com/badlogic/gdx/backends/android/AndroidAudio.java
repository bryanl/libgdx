/*******************************************************************************
 * Copyright 2010 Mario Zechner (contact@badlogicgames.com)
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
package com.badlogic.gdx.backends.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * An implementation of the {@link Audio} interface for Android.
 * 
 * @author mzechner
 *
 */
final class AndroidAudio implements Audio
{
	private  SoundPool soundPool;
	private final AudioManager manager;
	protected final List<AndroidMusic> musics = new ArrayList<AndroidMusic>( );
	protected final List<Boolean> wasPlaying = new ArrayList<Boolean>( );
	
	AndroidAudio( Activity context )
	{
		soundPool = new SoundPool( 16, AudioManager.STREAM_MUSIC, 100);		
		manager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		context.setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}
	
	protected void pause( )
	{
		wasPlaying.clear();
		for( AndroidMusic music: musics )
		{
			if( music.isPlaying() )
			{
				music.pause();
				wasPlaying.add( true );
			}
			else
				wasPlaying.add( false );
		}
	}
	
	protected void resume( )
	{
		for( int i = 0; i < musics.size(); i++ )
		{
			if( wasPlaying.get(i) )		
				musics.get(i).play();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AudioDevice newAudioDevice(boolean isMono) 
	{
		return new AndroidAudioDevice( isMono );
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Music newMusic(FileHandle file) 
	{
		AndroidFileHandle aHandle = (AndroidFileHandle)file;
		
		
		MediaPlayer mediaPlayer = new MediaPlayer();
		
		if( aHandle.isAsset() )
		{
			try
			{
				AssetFileDescriptor descriptor = aHandle.getAssetManager().openFd( aHandle.getFileName() );
				mediaPlayer.setDataSource( descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength() );
				descriptor.close();
				mediaPlayer.prepare();				
				AndroidMusic music = new AndroidMusic( this, mediaPlayer );
				musics.add( music );
				return music;
			}
			catch( Exception ex )
			{
				ex.printStackTrace();				
				return null;
			}
		}
		else
		{
			try
			{				
				mediaPlayer.setDataSource( aHandle.getFileName() );
				mediaPlayer.prepare();				
				AndroidMusic music = new AndroidMusic( this, mediaPlayer );
				musics.add( music );
				return music;
			}
			catch( Exception ex )
			{
				ex.printStackTrace();				
				return null;
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sound newSound(FileHandle file) 
	{
		AndroidFileHandle aHandle = (AndroidFileHandle)file;
		if( aHandle.isAsset() )
		{
			try {
				AssetFileDescriptor descriptor = aHandle.getAssetManager().openFd( aHandle.getFileName() );
				AndroidSound sound = new AndroidSound( soundPool, manager, soundPool.load( descriptor, 1) );
				descriptor.close();
				return sound;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		else
		{
			try
			{
				return new AndroidSound( soundPool, manager, soundPool.load(aHandle.getFileName(), 1) );
			}
			catch( Exception e )
			{
				e.printStackTrace();
				return null;
			}
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AudioRecorder newAudioRecoder(int samplingRate, boolean isMono) {
		return new AndroidAudioRecorder( samplingRate, isMono );
	}

	/**
	 * Kills the soundpool and all other resources
	 */
	public void dispose() 
	{	
		soundPool.release();
	}
}
