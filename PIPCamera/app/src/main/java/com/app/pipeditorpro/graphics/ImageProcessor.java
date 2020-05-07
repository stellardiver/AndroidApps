package com.app.pipeditorpro.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.app.pipeditorpro.graphics.commands.ImageProcessingCommand;

import java.util.LinkedList;

public class ImageProcessor {

	private static ImageProcessor instance = null;
	private  boolean modified = false;

	private ImageProcessorListener processListener;

	private LinkedList<ImageProcessingCommand> queue;
	private Bitmap savedBitmap;
	private Bitmap lastResultBitmap;

	private Context applicationContext;
	private Handler uiThreadHandler;
	
	
	public  boolean isModified(){
		return modified;
		
	}
	public  void resetModificationFlag(){
		 modified = false;
		
	}
	
	public static ImageProcessor getInstance() {
		if (instance == null) {
			instance = new ImageProcessor();
		}
		return instance;
	}

	private ImageProcessor() {
		queue = new LinkedList<ImageProcessingCommand>();
		workingThread.start();
	}


	private Thread workingThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				ImageProcessingCommand cmd;
				try {
					synchronized (queue) {
						while (queue.isEmpty()) {
							queue.wait();
						}
						cmd = queue.poll();
					}
					onProcessStart();
					lastResultBitmap = cmd.process(savedBitmap);
					cmd = null;
					onProcessEnd();
					modified=true;
				} catch (InterruptedException e) {
					break;
				}
			}
		}
		
		private void onProcessStart() {
			if (uiThreadHandler!= null && processListener != null) {
				uiThreadHandler.post(new Runnable() {
					@Override
					public void run() {
						processListener.onProcessStart();
					}
				});
			}
		}

		private void onProcessEnd() {

			if (uiThreadHandler!= null && processListener != null) {
				uiThreadHandler.post(new Runnable() {
					@Override
					public void run() {
						processListener.onProcessEnd(lastResultBitmap);	
					}
				});
			}
		}
	});


	public void setBitmap(Bitmap bitmap) {
		this.savedBitmap = bitmap;
	}

	public Bitmap getBitmap() {
		return savedBitmap;
	}

	public void runCommand(ImageProcessingCommand command) {
		conditionallyAddToQueue(command);
	}

	private void conditionallyAddToQueue(ImageProcessingCommand command) {
		synchronized (queue) {
			if (!queue.isEmpty()) {
				ImageProcessingCommand c = queue.getLast();
				if (c.getId().equals(command.getId())) {
					queue.removeLast();
				}
			}
			queue.add(command);
			queue.notify();
		}
	}

	public void save() {
		if (lastResultBitmap != null) {

			savedBitmap = lastResultBitmap;
			
			ImageProcessor.getInstance().setBitmap(savedBitmap);
			lastResultBitmap = null;
		}
	}

	public ImageProcessorListener getProcessListener() {
		return processListener;
	}

	public void setProcessListener(ImageProcessorListener processListener) {
		this.processListener = processListener;
	}

	public void clearProcessListener() {
		this.processListener = null;
		if (lasResultCanBeRecycled()) {
			lastResultBitmap.recycle();
		}
		this.lastResultBitmap = null;
	}

	private boolean lasResultCanBeRecycled() {
		return lastResultBitmap != savedBitmap && lastResultBitmap != null;
	}

	public Bitmap getLastResultBitmap() {
		return lastResultBitmap;
	}

	public void setLastResultBitmap(Bitmap lastResultBitmap) {
		this.lastResultBitmap = lastResultBitmap;
	}

	public void setApplicationCotnext(Context applicationContext){
		if (this.applicationContext == null){
			uiThreadHandler = new Handler();
		}
		this.applicationContext = applicationContext;
	}

}
