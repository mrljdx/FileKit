<div align="center">
  <img src="https://github.com/user-attachments/assets/78fbdb24-6210-4d41-afeb-57d199a743f8" alt="FileKit for Kotlin Multiplatform and Compose Multiplatform" />

  <br>

  <h1>FileKit</h1>
  <p>🚀 A powerful cross-platform file operations library for Kotlin Multiplatform</p>

  <div>
    <img src="https://img.shields.io/maven-central/v/io.github.vinceglb/filekit-core" alt="FileKit Kotlin Maven Version" />
    <img src="https://img.shields.io/badge/Platform-Android-brightgreen.svg?logo=android" alt="Badge Android" />
		<img src="https://img.shields.io/badge/Platform-iOS%20%2F%20macOS-lightgrey.svg?logo=apple" alt="Badge iOS" />
		<img src="https://img.shields.io/badge/Platform-JVM-8A2BE2.svg?logo=openjdk" alt="Badge JVM" />
    <img src="https://img.shields.io/badge/Platform-WASM%20%2F%20JS-yellow.svg?logo=javascript" alt="Badge JS" />
  </div>

  <br>
</div>

## ✨ Features

- 📱 **Cross-Platform Support**: Works seamlessly on Android, iOS, macOS, JVM (Windows, macOS, Linux), JS, and WASM
- 🎯 **Native Experience**: Uses platform-native file pickers for the best user experience
- 🪶 **Lightweight**: Minimal dependencies to keep your app fast and lean
- 🔌 **Rich Integrations**: Works with Compose Multiplatform, Coroutines, kotlinx-io, Coil, and more
- 📂 **Comprehensive File Operations**: Pick files, save documents, access photos, manage directories
- 🎨 **Image Utilities**: Built-in image compression and gallery integration

## 📚 Documentation

Visit our [documentation](https://filekit.mintlify.app) to learn more: https://filekit.mintlify.app

- [FileKit Introduction](https://filekit.mintlify.app/introduction)
- [Getting Started Guide](https://filekit.mintlify.app/quickstart)
- [Core API Documentation](https://filekit.mintlify.app/core/setup)
- [Dialogs Documentation](https://filekit.mintlify.app/dialogs/setup)

> Migrate from FileKit 0.8 to 0.10 by following the [migration guide](https://filekit.mintlify.app/migrate-to-v0.10).

## 🚀 Quick Start

### Installation

```kotlin
// build.gradle.kts
dependencies {
    implementation("io.github.vinceglb:filekit-core:$version")
    implementation("io.github.vinceglb:filekit-dialogs:$version")
    implementation("io.github.vinceglb:filekit-dialogs-compose:$version")
    implementation("io.github.vinceglb:filekit-coil:$version")
}
```

Follow the [installation guide](https://filekit.mintlify.app/installation) to learn how to install and setup FileKit in your project.

### Usage Examples

```kotlin
// Pick a file
val file = FileKit.openFilePicker()

// Pick multiple files
val files = FileKit.openFilePicker(mode = FileKitMode.Multiple())

// Pick only images
val imageFile = FileKit.openFilePicker(type = FileKitType.Image)

// Pick a directory
val directory = FileKit.openDirectoryPicker()

// Save a file
val contentToSave = "Hello FileKit!"
val file = FileKit.openFileSaver(suggestedName = "document", extension = "txt")
file?.writeString(contentToSave)

// Work with files
val myFile = FileKit.filesDir / "document.pdf"
println(myFile.name)
println(myFile.size())
myFile.writeString("Hello, World!")

// Image operations
val compressedBytes = FileKit.compressImage(
    bytes = imageFile.readBytes(),
    quality = 80,
    maxWidth = 1024,
    maxHeight = 1024
)
```

See the [quickstart guide](https://filekit.mintlify.app/quickstart) to learn more about the different file operations and utilities available in FileKit.

## 📦 Modular Structure

FileKit is designed to be modular, allowing you to include only what you need:

- **FileKit Core**: Basic file operations and the `PlatformFile` abstraction
- **FileKit Dialogs**: File pickers and save dialogs
- **FileKit Dialogs Compose**: Compose Multiplatform integration
- **FileKit Coil**: Integration with Coil for image loading

![FileKit Preview](https://github.com/vinceglb/FileKit/assets/24540801/e8a7bc49-41cc-4632-84c4-1013fd23dd76)

## 🙏 Credits

FileKit stands on the shoulders of giants. Special thanks to:
- [compose-multiplatform-file-picker](https://github.com/Wavesonics/compose-multiplatform-file-picker)
- [peekaboo](https://github.com/onseok/peekaboo)
- [Calf](https://github.com/MohamedRejeb/Calf)
- [jnafilechooser](https://github.com/steos/jnafilechooser)
- [swing-jnafilechooser](https://github.com/DJ-Raven/swing-jnafilechooser)
- [nativefiledialog](https://github.com/mlabbe/nativefiledialog)
- [IFileDialogImp](https://github.com/dbwiddis/IFileDialogImp)
- [IntelliJ Community Foundation](https://github.com/JetBrains/intellij-community/blob/master/platform/util/ui/src/com/intellij/ui/mac/foundation/Foundation.java)
- [file_picker (flutter)](https://pub.dev/packages/file_picker)

---

<div align="center">
  <p>Made with ❤️ by <a href="https://github.com/vinceglb">Vince</a></p>
  <!-- <p>If you find FileKit helpful, please consider giving it a ⭐️</p> -->
</div>
