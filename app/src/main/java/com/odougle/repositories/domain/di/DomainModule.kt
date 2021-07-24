package com.odougle.repositories.domain.di

import com.odougle.repositories.domain.ListUserRepositoriesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {
    fun load(){
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            //always return a new instance for this use case
            factory {
                ListUserRepositoriesUseCase(get())
            }
        }
    }
}