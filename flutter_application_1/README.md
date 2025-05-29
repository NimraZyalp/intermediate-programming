## Reflection

### Learning & Implementation
- **Getting Started**  
  Coding with Flutter for the first time was exciting. I followed the official Flutter “Write Your First App” tutorial to understand the widget tree and state management. Building the UI with `StatelessWidget` and `StatefulWidget` felt natural once I grasped the reactive model.
- **State Management**  
  I learned how to manage API calls in a stateful widget, using `setState` to trigger UI updates. Understanding when to show a loading indicator versus displaying data took some trial and error.
- **Networking in Dart**  
  Implementing HTTP requests with the `http` package was straightforward. Parsing JSON into Dart maps and extracting nested fields (`data['current']['temp_c']`) furthered my understanding of Dart’s `Map<String, dynamic>` typing.

### Resources Used
- **Official Documentation**  
  - Flutter Widgets: https://docs.flutter.dev/development/ui/widgets  
  - Dart HTTP Package: https://pub.dev/packages/http  
- **YouTube Tutorials**  
  - “Flutter Crash Course” by Net Ninja  
  - “Flutter TDD Clean Architecture Course” by Reso Coder  
- **API**
  - WeatherAPI docs: https://www.weatherapi.com/docs/  

### Key Classes, Interfaces & Functions
- **Inherited from Flutter**  
  - `StatelessWidget` → `WeatherApp`  
  - `StatefulWidget` → `WeatherHomePage`  
  - `State<WeatherHomePage>` → `_WeatherHomePageState`
- **Controllers & Widgets**  
  - `TextEditingController` for handling text input  
  - `TextField`, `ElevatedButton`, `Scaffold`, `AppBar`, `Padding`, `Column`  
- **Networking**  
  - `http.get(Uri url)` to fetch data  
  - `json.decode(String body)` to parse JSON  
  - `Future<void> fetchWeather(String city)` for the API logic


I also figured out how to make README's look good for this reflection :)
