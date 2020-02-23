# ElAbda3Task
Implementation of a native app that shows Jake Wharton Github repositories as a list. 
Each item on list should contain basic info such as (owner image - repository name – views ..etc.)

## SPECEFICATIONS
- Use pagination (request 15 items per request). 
- When the user reaches the third item from the bottom, request another batch.
- During the request execution, you can show a progress bar as the last item in the list. If it's clear that there are no more items available, you should stop doing requests and showing the progress bar.
- Make sure that the app will be usable (at least partially) without an internet connection.
- Notify the user if a request was failed, but make sure that the user will be able to see locally stored data.
- The Data https://api.github.com/users/JakeWharton/repos?page=1&per_page=15

## ACCEPTANCE CRITERIA:
- App should be implemented using MVVM and 
- Clean architecture 
- Using latest version of architecture component (Room – View Model – Data Binding ..etc.).
- preferred to use the following technologies  (RXJava - coroutines - Dagger – Retrofit ,…..).
- App should be delivered in 1 day with all required functionalities
- In case you think you will miss the deadline please push your code and continue working and enhancing in subsequent commits.
- Enhancing app after pushing the code is more than welcome.
