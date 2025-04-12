# WeatherApp

WeatherApp is an Android application that provides real-time weather information for various cities. It utilizes the OpenWeatherMap API to fetch current weather data and forecasts, presenting the information in a user-friendly interface.

## Features

- **Current Weather Data**: Displays temperature, humidity, wind speed, and weather conditions for the selected city.
- **5-Day Forecast**: Provides a 5-day weather forecast with detailed information.
- **Search Functionality**: Allows users to search for cities to view their weather information.
- **Responsive UI**: Features a clean and responsive user interface designed with Material Design principles.

## Architecture

The application follows the MVVM (Model-View-ViewModel) architecture pattern, ensuring a clear separation of concerns and enhancing testability.

## Technologies Used

- **Kotlin**: Primary programming language.
- **Retrofit**: For handling API requests.
- **LiveData & ViewModel**: For managing UI-related data in a lifecycle-conscious way.
- **Data Binding**: To bind UI components in layouts to data sources.
- **Material Design Components**: For building a modern UI.

## Project Structure

The project is organized into the following packages:

- `api`: Contains classes related to API communication.
- `model`: Holds data classes representing the API responses.
- `repository`: Manages data operations and acts as a mediator between different data sources.
- `ui.theme`: Includes theming resources for the application.
- `view`: Contains UI components and activities/fragments.
- `viewmodel`: Holds ViewModel classes that provide data to the UI.

## Getting Started

### Prerequisites

- Android Studio Bumblebee or later.
- An API key from [OpenWeatherMap](https://openweathermap.org/api).

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/tarik-bratic/WeatherApp.git
   ```

2. Open the project in Android Studio.

3. Add your OpenWeatherMap API key to the project:

   - Create a `local.properties` file in the root directory (if it doesn't exist).
   - Add the following line:

     ```properties
     OPEN_WEATHER_MAP_API_KEY=your_api_key_here
     ```

4. Build and run the application on an emulator or physical device.
