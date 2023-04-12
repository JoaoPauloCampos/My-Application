### Android Studio Version 
Dolphin | 2021.3.1 Patch 1

### Architecture
MVVM with Clean Architecture
Jetpack Compose

I used MVVM architecture mixed with clean architecture, leaving the layers well separated, making it possible to test each layer separately. I used dependency injection via constructors, so that I can have more flexibility in creating tests, without depending on any injection mechanism to perform the tests. I also used some SOLID principles like Dependency Inversion and also designer patterns like Observable, State, Chain of responsibility.

The data layer is split between domain and remote.
## domain
-> In this layer we will have the definition of our interfaces that will be implemented in the remote layer, the data models that will already be properly prepared to be displayed on the screen, the mappers that will be responsible for transforming the information that is returned from the remote layer to our domain, our interactors which is where each use case of our application resides
    - Either -> It is a sealed class that has two types Success and Failure, having two output generics (S and F), it was created to be our return pattern, always delivering S or F, I used Nothing to signal the case that is not returned. It also helps lessen the verbosity of having to pass 2 lambdas to a repository implementation class, onSuccess() and onError() for example
    - UseCase -> I made use of coroutines to handle asynchronous transactions, CoroutineContextProvider has 2 contexts io and main, which is used by our UseCase to perform the call in io and return in main. Our use cases implement an abstract 'UseCase that was created, it has a run that will be used to make the call to our repository, which will be received in the constructor of each use case and always return an Either type . The abstract class also has an invocation operator, which is used by the consumers of each use case, in our case the ViewModels

## remote    
-> This is where our services and network layer is located, the data models that are defined and returned by the backend, the implementation of repositories and also a mechanism that I called RequestWrapper.
    - RequestWrapper -> It is a function that has a generic type D, which receives a suspend function as a parameter whose return is also of type D. It wraps my calls to services in a safe way, guaranteeing the capture of any exception that may occur, standardizing all Success or Failure responses. It can also be used for specific error handling, such as http exceptions, if you want to do specific error handling for the 401 error code, for example, we could run it here. In this case, to signal errors, I used a normal Exception, but we could have a customized data class here, allowing standardization of errors with message fields, etc.

### 3rd party libraries:
https://mockk.io/
https://insert-koin.io/
https://square.github.io/retrofit/

## Build
- Unzip the project and open the project on Android Studio
- Sync the project
- Run
- Important information about how the app works:
  Due to the problem with the public API not working, I used apiary.io to create a fake service, always returning the
  same data structure, regardless of the url sent.
  I left the URL of url-shortener/local-server commented out.
  For the app to work pointing to the local server you need to configure the app to accept protocol
  http https://developer.android.com/training/articles/security-config.

### Unit Tests

./gradlew testDebugUnitTest

### Instrumented Tests

./gradlew :app:connectedDebugAndroidTest
