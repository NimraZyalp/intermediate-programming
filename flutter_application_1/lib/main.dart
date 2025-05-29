import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

void main() => runApp(WeatherApp());

class WeatherApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Weather App',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: WeatherHomePage(),
    );
  }
}

class WeatherHomePage extends StatefulWidget {
  @override
  _WeatherHomePageState createState() => _WeatherHomePageState();
}

class _WeatherHomePageState extends State<WeatherHomePage> {
  final TextEditingController _controller = TextEditingController();
  String _temperature = '';
  String _description = '';
  bool _isLoading = false;

  Future<void> fetchWeather(String city) async {
  setState(() => _isLoading = true);
  final apiKey = '2c8453279ccd47b5880132533252205';  // API Key
  final url = Uri.parse(
    'https://api.weatherapi.com/v1/current.json'
    '?key=$apiKey'
    '&q=$city'
    '&aqi=no'
  );

  try {
    final response = await http.get(url);
    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      setState(() {
        _temperature = '${data['current']['temp_c']} °C';
        _description = data['current']['condition']['text'];
      });
    } else {
      setState(() {
        _temperature = 'Error: ${response.statusCode}';
        _description = '';
      });
    }
  } catch (e) {
    setState(() {
      _temperature = 'Error: $e';
      _description = '';
    });
  } finally {
    setState(() => _isLoading = false);
  }
}


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Weather App')),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            TextField(
              controller: _controller,
              decoration: InputDecoration(
                labelText: 'Enter city',
                border: OutlineInputBorder(),
                suffixIcon: IconButton(
                  icon: Icon(Icons.clear),
                  onPressed: () => _controller.clear(),
                ),
              ),
            ),
            SizedBox(height: 12),
            ElevatedButton(
              onPressed: _isLoading
                  ? null
                  : () {
                      final city = _controller.text.trim();
                      if (city.isNotEmpty) fetchWeather(city);
                    },
              child: Text(_isLoading ? 'Loading…' : 'Get Weather'),
            ),
            SizedBox(height: 24),
            if (_temperature.isNotEmpty) ...[
              Text('Temperature: $_temperature',
                  style: TextStyle(fontSize: 18)),
              SizedBox(height: 8),
              Text('Condition: $_description',
                  style: TextStyle(fontSize: 18)),
            ],
          ],
        ),
      ),
    );
  }
}
