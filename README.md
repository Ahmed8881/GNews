# Quiz2 (News App)

A simple Android news app demo built with Kotlin, Retrofit, Glide, Room, and ViewModel + LiveData.

**Status:** Prototype

**Screenshots**
- Main screen (shows new country dropdown):
  - ![Main screen](images/main_screen.png)
- Full article screen:
  - ![Article screen](images/article_screen.png)

(Place your screenshots in `images/` using the filenames above.)

**Features**
- Fetches headlines from GNews API
- Country selection dropdown on main screen
- Search and filter articles
- View full article details
- Offline caching with Room (basic)

**Quick Setup**
- Add your API key to `local.properties`:

```
NEWS_API_KEY=your_api_key_here
```

- Build and run (Windows):

```powershell
./gradlew.bat assembleDebug
./gradlew.bat installDebug
```

or (Unix/macOS):

```bash
./gradlew assembleDebug
./gradlew installDebug
```

**Where to find things**
- Main screen implementation: `app/src/main/java/com/example/quiz2/MainActivity.kt`
- News item layout: `app/src/main/res/layout/item_news_card.xml`
- Network client: `app/src/main/java/com/example/quiz2/network/ApiClient.kt`

**Add screenshots**
1. Create the folder `images/` at project root.
2. Place `main_screen.png` and `article_screen.png` there.
3. Reopen Git or your editor to view the images in the README.

**Notes & Next Steps**
- Consider making the colors theme-aware (dark/light) using resource qualifiers.
- I can add automated screenshot capture instructions or a sample CI job if you want.

**License**
MIT
