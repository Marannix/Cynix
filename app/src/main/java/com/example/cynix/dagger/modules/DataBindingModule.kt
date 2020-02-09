package com.example.cynix.dagger.modules

import com.example.cynix.dagger.module.ApiModule
import com.example.cynix.dagger.module.RoomModule
import dagger.Module

@Module (includes = [ApiModule::class, RoomModule::class])
abstract class DataBindingModule