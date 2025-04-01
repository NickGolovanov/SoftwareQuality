# Instructions to Install Maven on macOS

## Using Homebrew (if you have Homebrew installed):
1. Open your terminal and run the following command:
   ```bash
   brew install maven
   ```

## Manual Installation:
1. Download the latest version of Maven from the [Apache Maven website](https://maven.apache.org/download.cgi).
2. Extract the downloaded archive to a directory on your system (e.g., `/usr/local/apache-maven`).
3. Add the `bin` directory of the extracted folder to your `PATH` environment variable. You can do this by adding the following line to your `~/.zshrc` file:
   ```bash
   export PATH=/usr/local/apache-maven/bin:$PATH
   ```
4. After editing the file, run the following command to apply the changes:
   ```bash
   source ~/.zshrc
   ```

## Verify Installation
After installing Maven, verify the installation by running:
```bash
mvn -v
```

## Next Steps
Once Maven is installed and verified, you can run the tests again using the command:
```bash
cd test && mvn test
