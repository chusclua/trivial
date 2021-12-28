package com.chus.clua.trivial.domain.usecases

import com.chus.clua.trivial.domain.repositories.GameRepository
import javax.inject.Inject

class AddScoreUseCase @Inject constructor(private val gameRepository: GameRepository) {
    operator fun invoke(score: Int) = gameRepository.addScore(score)
}