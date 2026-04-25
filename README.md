
<!-- ABOUT THE PROJECT -->
## BPM Finder

Looking for a way to rapidly find the BPM or tempo of a song without attempting to research on your own or manually tapping it out?
This a basic CML application that uses Soundchart's database of songs to retrieve the bpm of the song.

You will need to generate your own API Key and identifier from their website [here](https://developers.soundcharts.com/documentation/getting-started), 
which gives 1000 free API calls, which should be enough for personal use. 

A sandbox key has already been set up for your convenience - however the sandbox is extremely limited the songs it includes.

If you want to read more about the sandbox, click [here](https://developers.soundcharts.com/documentation/sandbox-data).

### Prerequisites
[JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or higher.

[Gradle 8.8](https://docs.gradle.org/8.8/release-notes.html) or higher.

### Installation

0. (Optional) Get a free API Key at [https://developers.soundcharts.com/documentation/getting-started](https://developers.soundcharts.com/documentation/getting-started).
   
1. Clone the repo
   ```sh
   git clone https://github.com/yourusername/bpmfinder.git 
   ```
2. Build the project
   ```sh
   ./gradlew build
   ```
3. You should now be able to run your program in an IDE of your choice (IntelliJ, Eclipse, etc.)


<!-- ROADMAP -->
## Roadmap

- [x] Create basic boilerplate to connect to Soundcharts
- [x] Main menu programmed for use in the command line
- [ ] Include additional metadata
- [ ] Develop GUI using JavaFX/Swing


See the [open issues](https://github.com/AustinDHuynh/BPM-Finder/issues) for a full list of proposed features (and known issues).

<!-- CONTRIBUTING -->
## Contributing

If you have a suggestion that would make this better, please fork the repo and create a pull request. 

1. Fork the project.
2. Create your feature branch (`git checkout -b feature/feature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/feature`).
5. Open a pull request.

