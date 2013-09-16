package uk.co.polly.volleydemo;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class MemoryLruImageCache implements ImageCache  {
    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryLruImageCache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 10;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        final String key = String.valueOf(url.hashCode());
        return mMemoryCache.get(key);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        final String key = String.valueOf(url.hashCode());
        mMemoryCache.put(key, bitmap);
    }
}