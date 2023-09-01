import com.ztx.skeleton.presenter.model.RepositoryUiData
import com.ztx.skeleton.presenter.model.UserUiData
import kotlin.random.Random


val sampleRepositories
    get() = createSampleRepositories()

private fun createSampleRepositories(): List<RepositoryUiData> {
    val repositories = mutableListOf<RepositoryUiData>()
    for (i in 0..15) {
        repositories.add(
            RepositoryUiData(
                name = "repository${Random.nextInt(10000, Int.MAX_VALUE)}",
                owner = createSampleUser(i)
            )
        )
    }
    return repositories
}

val sampleUsers
    get() = createSampleUsers()

private fun createSampleUsers(): List<UserUiData> {
    val users = mutableListOf<UserUiData>()
    for (i in 0..15) {
        users.add(
            createSampleUser(i)
        )
    }
    return users
}

private fun createSampleUser(i: Int) = UserUiData(
    id = i + 1,
    login = "user${Random.nextInt(10000, Int.MAX_VALUE)}",
    avatarUrl = "https://www.github.com/placeholder"
)