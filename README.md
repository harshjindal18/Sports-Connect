# Sports Connect

##  Project Overview
**Sports Connect** is a platform designed to bridge the gap in sports awareness in India by promoting lesser-known sports and connecting athletes with training centers and academies. It provides an intuitive interface for users to explore different sports, find nearby training centers, and access valuable sports-related resources.

##  Features
- **User-Friendly Interface**: Simple and intuitive design for easy navigation.
- **Location-Based Search**: Uses geolocation to find nearby sports academies and events.
- **Comprehensive Sports Database**: Information on various sports, including rules, training centers, and famous athletes.
- **Recommendation System**: Suggests sports, academies, or articles based on user interests.
- **Content Moderation**: AI-powered filtering of spam and inappropriate content.

##  Tech Stack
### **Frontend:**
- HTML
- CSS

### **Backend:**
- Java (Spring Boot)
- REST API

### **Database:**
- MySQL (Stores user profiles, sports data, and academy details)

### **Cloud & Deployment:**
- AWS for cloud-based, serverless infrastructure
- HTTPS for secure data transmission

##  Project Structure
```
Sports-Connect/
│── src/
│   ├── main/
│   │   ├── java/com/sportsconnect/ (Backend Logic)
│   │   ├── resources/templates/ (Frontend Pages)
│── database/
│── docs/
│── README.md
│── .gitignore
│── pom.xml
```

##  Installation & Setup
### Prerequisites
- Java 17+
- MySQL
- Maven
- Git

### Steps to Run Locally
1. **Clone the Repository:**
   ```sh
   git clone https://github.com/harshjindal18/automation_test_500107226.git
   cd automation_test_500107226
   ```
2. **Configure MySQL Database:**
   - Update `application.properties` with your MySQL credentials.
3. **Build and Run the Application:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. **Access the Application:**
   - Open `http://localhost:8080` in a browser.

##  Contributing
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch: `git checkout -b feature-branch`.
3. Commit changes: `git commit -m 'Add new feature'`.
4. Push to your branch: `git push origin feature-branch`.
5. Open a Pull Request.

##  Security & Privacy
- Data encryption for sensitive information.
- Role-based access control for administrators.
- Compliance with GDPR and CCPA standards.


