# Quiz2 (News App)

A simple Android news app demo built with Kotlin, Retrofit, Glide, Room, and ViewModel + LiveData.

**Status:** Prototype

**Screenshots**
- Main screen (shows new country dropdown):
  <img width="246" height="532" alt="image" src="https://github.com/user-attachments/assets/491eba9a-7783-49cd-a8a8-c838c63f872a" />
<img width="235" height="533" alt="image" src="https://github.com/user-attachments/assets/e26f7abe-f5d4-4317-8bc5-5c05ad869139" />

- Full article screen:
  <img width="240" height="512" alt="image" src="https://github.com/user-attachments/assets/336a4ed3-7168-485c-92b3-b402e4bf187e" />




**Features**
- Fetches headlines from GNews API
- Country selection dropdown on main screen
- Search and filter articles
- View full article details
- Offline caching with Room (basic)

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
