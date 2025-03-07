# GitHub User Info Fetcher

## 📌 Project Goal
This terminal-based application is implemented in **Kotlin** and fetches GitHub user information from the API, storing it in memory to prevent redundant requests. The retrieved information includes:
- Username
- Number of followers
- Number of following
- Account creation date
- List of the user's public repositories

## 🏗️ Program Menu Structure
1️⃣ Retrieve user information based on username  
2️⃣ Display the list of users stored in memory  
3️⃣ Search for a user by username in the stored data  
4️⃣ Search for a repository name in the stored data  
5️⃣ Update stored users information  
6️⃣ Exit the program  

## 🔧 Technical Specifications
✅ Use the **official GitHub API** to fetch user information  
✅ Store data in **memory cache** to prevent redundant API requests  
✅ Optionally save retrieved data to a **file**  
✅ Implement **error handling** for API interactions  
✅ Utilize **design patterns** and **Kotlin features** to ensure **scalability and maintainability**  
✅ Use **Retrofit** and **Gson** libraries for API communication and data management  

## 📌 Resources and Documentation
- **GitHub API Docs:** [View API Documentation](https://docs.github.com/en/rest?apiVersion=2022-11-28)
- **Retrofit:** [Retrofit Documentation](https://square.github.io/retrofit)

## 🚀 How to Run
1. Install **Kotlin** and **Gradle**.
2. Clone the project:
   ```sh
   git clone https://github.com/AliMajidi1/githubuserfetcher.git
   cd github-user-fetcher
   ```
3. Run the project:
   ```sh
   ./gradlew run
   ```

## 💡 Important Notes
- A **GitHub token** is required to fetch user information and avoid API rate limits.
- **In-memory caching** is used to reduce unnecessary API calls.
- Data can optionally be stored in a **local file** instead of memory.

