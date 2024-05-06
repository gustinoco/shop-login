package shoplogin
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.shop.commons.ShopNetwork
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import br.com.shop.login.model.LoginModel
import br.com.shop.login.repository.LoginRepository
import br.com.shop.login.repository.LoginResponse
import br.com.shop.login.usecase.LoginUseCase
import br.com.shop.login.viewmodel.LoginViewEvent
import br.com.shop.login.viewmodel.LoginViewModel
import br.com.shop.login.viewmodel.LoginViewState

@ExtendWith(CoroutineTestExtension::class)
class LoginViewModelTest {

    @Rule @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: LoginRepository

    private lateinit var viewModel: LoginViewModel

    @MockK
    private lateinit var useCase: LoginUseCase
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        MockKAnnotations.init(this)

        val shopNetwork: ShopNetwork = mockk(relaxed = true)
        repository = LoginRepository(shopNetwork)
        viewModel = LoginViewModel(repository)
    }

    @AfterEach
    fun afterEach() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login with empty cpf should set error state`() = runBlockingTest {
        viewModel.login("", "password")
        assert(viewModel.viewEvent.value is LoginViewEvent.Error)
    }

    @Test
    fun `login with empty password should set error state`() = runBlockingTest {
        viewModel.login("01234567891", "")
        assert(viewModel.viewEvent.value is LoginViewEvent.Error)
    }

    @Test
    fun `login with errorCPf min 11 should set error state`() = runBlockingTest {
        viewModel.login("cpf", "")
        assert(viewModel.viewEvent.value is LoginViewEvent.Error)
    }

    @Test
    fun `login with valid cpf and password should set success state`() = runBlockingTest {
        coEvery {
            useCase.login("01234567891", "password")
        } returns LoginModel("01234567891", "Success")

        coEvery {
            repository.postLogin(any())
        } returns LoginResponse("01234567891", "Success", "")
        viewModel.login("01234567891", "password")
        assert(viewModel.viewState.value is LoginViewState.Success)

    }
}

