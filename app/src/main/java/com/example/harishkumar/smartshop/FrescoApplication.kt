package com.example.harishkumar.smartshop

import android.app.Application
import com.example.harishkumar.smartshop.cache.ImagePipelineConfigFactory
import com.facebook.drawee.backends.pipeline.Fresco

class FrescoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this, ImagePipelineConfigFactory.getImagePipelineConfig(this))
    }

}
