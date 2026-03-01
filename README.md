# DSA (Data Structures & Algorithms)
# 🚀 Java OOPS Practice – GitHub Codespaces Guide

This repository is used to practice Object-Oriented Programming concepts in Java.

You will use **GitHub Codespaces** to write, compile, and run Java programs directly in the browser.

---

# 💻 How to Open Codespaces

1. Go to this repository.
2. Click the green **Code** button.
3. Select the **Codespaces** tab.
4. Click **Create codespace on main**.
5. Wait for the environment to load.

---

# 🔄 Pull the Latest Code (Important)

If the instructor updates the repository, you must pull the latest changes.

Open **Terminal → New Terminal** and run:

```bash
git checkout main
git pull
```

---

# 🛠 Compile a Java File

To compile the main Java file:

```bash
javac OOPSSimpleDemo.java
```

If there are no errors, compilation was successful.

This command creates a `.class` file (compiled version of your program).

---

# ▶ Run the Java Program

After compiling, run:

```bash
java OOPSSimpleDemo
```

⚠️ Important:
- Do NOT include `.java` when running.
- Only use the class name.

---

# 📦 Compile All Java Files (If Multiple Files Exist)

If your project contains multiple `.java` files:

```bash
javac *.java
```

Then run the main class:

```bash
java OOPSSimpleDemo
```

---

# 💾 Save and Push Your Work

After making changes, save your work to GitHub:

```bash
git status
git add .
git commit -m "Completed assignment"
git push
```

Your work is now saved in your GitHub repository.

---

# ❗ Common Errors

### Error: 
```
can't find main(String[]) method
```

✔ Make sure:
- You are running the correct class.
- The class contains:

```java
public static void main(String[] args)
```

---

Happy Coding! 🚀
