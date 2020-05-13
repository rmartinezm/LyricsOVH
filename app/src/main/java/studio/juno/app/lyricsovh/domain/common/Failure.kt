package studio.juno.app.lyricsovh.domain.common

/**
 * Base Class for handling failures.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {

    /**
     *
     *
     */
    object NetworkConnectionFailure : Failure()

    /**
     *
     *
     */
    data class ServerFailure(
        val message: String? = null,
        val code: Int? = null
    ) : Failure()

    /**
     * Extend this class for feature specific failures.
     */
    abstract class FeatureFailure: Failure()

}
