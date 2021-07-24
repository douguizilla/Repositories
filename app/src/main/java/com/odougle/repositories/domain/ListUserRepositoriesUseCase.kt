package com.odougle.repositories.domain

import com.odougle.repositories.core.UseCase
import com.odougle.repositories.data.model.Repo
import com.odougle.repositories.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUseCase(
    private val repository: RepoRepository,

): UseCase<String, List<Repo>>() {
    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }
}