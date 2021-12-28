package com.chus.clua.trivial.domain.usecases

import com.chus.clua.trivial.domain.repositories.GameRepository
import javax.inject.Inject

class SaveNewRecordUseCase @Inject constructor(private val gameRepository: GameRepository) {
    operator fun invoke() = gameRepository.putRecord()
}