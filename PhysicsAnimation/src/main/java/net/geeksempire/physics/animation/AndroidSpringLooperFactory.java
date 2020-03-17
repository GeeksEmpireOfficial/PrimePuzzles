/*
 * Copyright © 2020 By ...
 *
 * Created by Elias Fazel on 3/17/20 2:03 PM
 * Last modified 3/17/20 12:52 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package net.geeksempire.physics.animation;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Choreographer;

import net.geeksempire.physics.animation.Core.SpringLooper;


abstract class AndroidSpringLooperFactory {

  /**
   * Create an Android {@link net.geeksempire.physics.animation.SpringLooper} for the detected Android platform.
   * @return a SpringLooper
   */
  public static SpringLooper createSpringLooper() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      return ChoreographerAndroidSpringLooper.create();
    } else {
      return LegacyAndroidSpringLooper.create();
    }
  }

  /**
   * The base implementation of the Android spring looper, using a {@link Handler} for the
   * frame callbacks.
   */
  private static class LegacyAndroidSpringLooper extends SpringLooper {

    private final Handler mHandler;
    private final Runnable mLooperRunnable;
    private boolean mStarted;
    private long mLastTime;

    /**
     * @return an Android spring looper using a new {@link Handler} instance
     */
    public static SpringLooper create() {
      return new LegacyAndroidSpringLooper(new Handler());
    }

    public LegacyAndroidSpringLooper(Handler handler) {
      mHandler = handler;
      mLooperRunnable = new Runnable() {
        @Override
        public void run() {
          if (!mStarted || mSpringSystem == null) {
            return;
          }
          long currentTime = SystemClock.uptimeMillis();
          mSpringSystem.loop(currentTime - mLastTime);
          mLastTime = currentTime;
          mHandler.post(mLooperRunnable);
        }
      };
    }

    @Override
    public void start() {
      if (mStarted) {
        return;
      }
      mStarted = true;
      mLastTime = SystemClock.uptimeMillis();
      mHandler.removeCallbacks(mLooperRunnable);
      mHandler.post(mLooperRunnable);
    }

    @Override
    public void stop() {
      mStarted = false;
      mHandler.removeCallbacks(mLooperRunnable);
    }
  }

  /**
   * The Jelly Bean and up implementation of the spring looper that uses Android's
   * {@link Choreographer} instead of a {@link Handler}
   */
  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  private static class ChoreographerAndroidSpringLooper extends SpringLooper {

    private final Choreographer mChoreographer;
    private final Choreographer.FrameCallback mFrameCallback;
    private boolean mStarted;
    private long mLastTime;

    /**
     * @return an Android spring choreographer using the system {@link Choreographer}
     */
    public static ChoreographerAndroidSpringLooper create() {
      return new ChoreographerAndroidSpringLooper(Choreographer.getInstance());
    }

    public ChoreographerAndroidSpringLooper(Choreographer choreographer) {
      mChoreographer = choreographer;
      mFrameCallback = new Choreographer.FrameCallback() {
        @Override
        public void doFrame(long frameTimeNanos) {
          if (!mStarted || mSpringSystem == null) {
            return;
          }
          long currentTime = SystemClock.uptimeMillis();
          mSpringSystem.loop(currentTime - mLastTime);
          mLastTime = currentTime;
          mChoreographer.postFrameCallback(mFrameCallback);
        }
      };
    }

    @Override
    public void start() {
      if (mStarted) {
        return;
      }
      mStarted = true;
      mLastTime = SystemClock.uptimeMillis();
      mChoreographer.removeFrameCallback(mFrameCallback);
      mChoreographer.postFrameCallback(mFrameCallback);
    }

    @Override
    public void stop() {
      mStarted = false;
      mChoreographer.removeFrameCallback(mFrameCallback);
    }
  }
}