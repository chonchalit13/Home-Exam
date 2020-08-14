package th.co.toei.homeexam.usecase

import th.co.toei.homeexam.model.Result

abstract class CoroutinesUseCase<in P, R> {
    abstract suspend fun execute(parameter: P): Result<R>
}