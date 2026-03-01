# Java Strings — Complete Concepts Guide
### All Methods + Time & Space Complexity

---

## How to Run

```bash
javac StringConcepts.java
java StringConcepts
```

> Requires Java 8 or higher. Check with `java -version`.

---

## What is a String in Java?

A **String** is a sequence of characters stored as an object in Java. Unlike primitive types like `int` or `char`, `String` is a full class with dozens of built-in methods.

The single most important thing to understand about Java Strings:

> **Strings are IMMUTABLE.**  
> Once created, the content of a String object can NEVER be changed.  
> Every method that appears to "modify" a String actually creates and returns a **new** String object.

---

## Key Classes at a Glance

| Class | Mutable? | Thread-Safe? | Use When |
|-------|----------|--------------|----------|
| `String` | ❌ No | ✅ Yes | Reading, comparing, passing around |
| `StringBuilder` | ✅ Yes | ❌ No | Building strings in single-threaded code |
| `StringBuffer` | ✅ Yes | ✅ Yes | Building strings in multi-threaded code |

---

## File Structure

```
StringConcepts.java
│
├── demoStringCreation()       → String Pool, literals vs new, intern()
├── demoStringComparison()     → ==, equals(), compareTo(), sorting
├── demoCoreStringMethods()    → All 60+ methods explained + complexity
├── demoImmutability()         → Why immutability matters, the + trap
├── demoConcatenation()        → + vs StringBuilder timing, join(), format()
├── demoStringBuilder()        → append, insert, delete, replace, reverse
├── demoSplitJoin()            → CSV parsing, split(), join(), regex
├── demoStringAlgorithms()     → Palindrome, anagram, frequency, reverse words
│
├── isPalindrome(s)            → Two-pointer technique
├── isAnagram(s1, s2)          → Frequency array technique
├── charFrequency(s)           → HashMap character count
├── reverseWords(sentence)     → Split + reverse + join
├── countVowels(s)             → Linear scan
├── longestCommonPrefix(strs)  → Progressive shrink
│
└── main()                     → Runs all sections + summary table
```

---

## Section 1 — String Creation & the String Pool

### Two Ways to Create a String

```java
String s1 = "hello";              // String Literal  → goes to String Pool
String s2 = new String("hello");  // new keyword     → goes to regular Heap
String s3 = "hello";              // Literal         → REUSES s1 from pool
```

### Memory Diagram

```
String Pool (special area inside Heap):
┌─────────────────────────────────────┐
│  "hello"  ◄─── s1 and s3 point here (SAME object!)
│  "world"  ◄─── other literals
└─────────────────────────────────────┘

Regular Heap:
┌─────────────────────────────────────┐
│  "hello"  ◄─── s2 (NEW object, different address)
└─────────────────────────────────────┘
```

### Why does the String Pool exist?

Strings are the most used objects in any Java program. The pool avoids creating duplicate objects for the same value — saving significant memory. When you write `String s = "hello"` fifty times in your program, only **one** `"hello"` object exists in memory.

### The `==` Trap

```java
s1 == s3   // true  ← same pool object, same memory address
s1 == s2   // false ← different memory locations, even though content is identical
```

`==` compares **references (memory addresses)**, not content. This is one of the most common bugs in Java interviews and exams.

```java
s1.equals(s2)  // true ← correct content comparison, always use this
```

### `intern()`

```java
String s4 = s2.intern();  // puts s2's content into pool (or returns existing)
s1 == s4   // true ← now both point to same pool object
```

### Complexity

| Operation | Time | Space |
|-----------|------|-------|
| String literal creation | O(n) | O(n) — n characters |
| `==` comparison | O(1) | O(1) — just addresses |
| `.equals()` | O(n) | O(1) — char by char |

---

## Section 2 — String Comparison

### The Four Ways to Compare

```java
s1 == s2                          // reference comparison — almost never what you want
s1.equals(s2)                     // content comparison — case sensitive
s1.equalsIgnoreCase(s2)           // content comparison — case insensitive
s1.compareTo(s2)                  // lexicographic — returns int
```

### `compareTo()` Return Values

```java
"Apple".compareTo("Banana")  // negative — A comes before B in alphabet
"Banana".compareTo("Apple")  // positive — B comes after A
"Apple".compareTo("Apple")   // 0        — identical
```

The return value is the **difference of the first mismatching characters' Unicode values**.

```java
// Used internally by Arrays.sort() and Collections.sort()
String[] names = {"Ravi", "Navaneeth", "Priya"};
Arrays.sort(names);  // uses compareTo → O(n log n)
// Result: ["Navaneeth", "Priya", "Ravi"]
```

### Complexity

| Operation | Time | Space |
|-----------|------|-------|
| `equals()` | O(n) | O(1) |
| `equalsIgnoreCase()` | O(n) | O(1) |
| `compareTo()` | O(n) worst case | O(1) |
| `Arrays.sort(String[])` | O(n log n) | O(log n) |

---

## Section 3 — Core String Methods

### All Critical Methods with Complexity

#### `length()` — O(1)
```java
"hello".length()  // 5 — stored as a field, not counted on each call
```

#### `charAt(index)` — O(1)
```java
"Navaneeth".charAt(0)  // 'N' — direct array index access, like arr[i]
```

#### `substring(start, end)` — O(n)
```java
"navaneeth@gmail.com".substring(0, 9)   // "navaneeth" — creates NEW String
"navaneeth@gmail.com".substring(10)     // "gmail.com" — from index to end
```
Every `substring()` creates a new String object by copying characters. This is why it's O(n) in both time and space.

#### `indexOf(str)` — O(n × m)
```java
String url = "https://github.com/navaneeth";
url.indexOf('/')         // 5  — first occurrence
url.lastIndexOf('/')     // 18 — last occurrence
url.indexOf("github")    // 8  — substring search
```
`n` = string length, `m` = pattern length. Uses a naive search algorithm by default (not KMP).

#### `contains(), startsWith(), endsWith()` — O(n)
```java
"signbridge.java".contains("bridge")    // true
"signbridge.java".startsWith("sign")    // true
"signbridge.java".endsWith(".java")     // true
```
All internally call `indexOf()` or similar — O(n).

#### `replace()` vs `replaceAll()` — O(n)
```java
"98765-43210".replace("-", "")          // literal replacement — O(n)
"Hello   World".replaceAll("\\s+", " ") // regex replacement — O(n) but slower constant
```
`replaceAll()` compiles and runs a regex, so while Big-O is the same, the constant factor is larger.

#### `trim()` vs `strip()` — O(n)
```java
"  hello  ".trim()   // "hello" — removes ASCII whitespace (\t, \n, \r, space)
"  hello  ".strip()  // "hello" — removes Unicode whitespace too (Java 11+)
```
`strip()` is the modern replacement for `trim()` — handles more whitespace types.

#### `toCharArray()` — O(n)
```java
char[] arr = "hello".toCharArray();  // ['h','e','l','l','o'] — new array, O(n) copy
```

#### `isEmpty()` vs `isBlank()` — O(1) vs O(n)
```java
"".isEmpty()      // true  — O(1): just checks length == 0
"  ".isEmpty()    // false — spaces count as content
"  ".isBlank()    // true  — O(n): scans all characters for non-whitespace
```

### Full Complexity Table

| Method | Time | Space | Why |
|--------|------|-------|-----|
| `length()` | O(1) | O(1) | Stored as field |
| `charAt(i)` | O(1) | O(1) | Direct array index |
| `indexOf(str)` | O(n×m) | O(1) | Substring search |
| `substring(s, e)` | O(n) | O(n) | Creates new String |
| `toLowerCase/Upper()` | O(n) | O(n) | New String |
| `trim()` / `strip()` | O(n) | O(n) | New String |
| `replace()` | O(n) | O(n) | New String |
| `replaceAll()` | O(n) | O(n) | Regex + new String |
| `contains()` | O(n) | O(1) | Calls indexOf |
| `startsWith()` | O(m) | O(1) | m = prefix length |
| `endsWith()` | O(m) | O(1) | m = suffix length |
| `toCharArray()` | O(n) | O(n) | Copies all chars |
| `isEmpty()` | O(1) | O(1) | Checks length == 0 |
| `isBlank()` | O(n) | O(1) | Scans all chars |

---

## Section 4 — String Immutability

### The Core Rule

```java
String original = "hello";
String upper    = original.toUpperCase();  // NEW object "HELLO"
String replaced = original.replace("l", "r"); // NEW object "herro"

System.out.println(original);  // still "hello" — UNCHANGED
```

No method ever changes `original`. Every transformation returns a new object.

### Why Immutability is a Feature, Not a Bug

**1. Thread Safety:** Multiple threads can read the same String simultaneously with zero synchronization needed — the content can never change under them.

**2. String Pool:** The pool only works because pooled strings never change. If `s1 = "hello"` could later become `"world"`, the pool would be corrupted.

**3. Security:** Passwords and file paths passed to security checks can't be modified by malicious code after validation.

**4. HashCode Caching:** String's `hashCode()` is computed once and cached internally. This makes String an ideal `HashMap` key — O(1) hash lookup every time.

### The O(n²) Trap — String + in a Loop

```java
// ❌ SLOW — O(n²) total
String result = "";
for (int i = 0; i < 10000; i++) {
    result = result + "a";  // creates a NEW String each iteration!
}
```

**Why it's O(n²):**
```
Iteration 1: copy 1  char  → new String of length 1
Iteration 2: copy 2  chars → new String of length 2
Iteration 3: copy 3  chars → new String of length 3
...
Iteration n: copy n  chars → new String of length n

Total copies = 1 + 2 + 3 + ... + n = n(n+1)/2 = O(n²)
```

For 10,000 iterations: **50,000,000 character copies** just to build a 10,000-character string.

```java
// ✅ FAST — O(n) total
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 10000; i++) {
    sb.append("a");  // modifies internal buffer — O(1) amortized
}
String result = sb.toString();
```

The live timing in the code demonstrates this gap with real milliseconds.

---

## Section 5 — String Concatenation Methods

### `String.join()` — O(n)

```java
// Clean, readable, no boilerplate
String csv     = String.join(", ", "Alice", "Bob", "Charlie");
// "Alice, Bob, Charlie"

List<String> tags = Arrays.asList("Java", "DSA", "OOP");
String tagLine = String.join(" → ", tags);
// "Java → DSA → OOP"
```

`String.join()` uses a `StringJoiner` internally, which uses `StringBuilder`. It is O(n) and clean to read.

### `String.format()` — O(n)

```java
String.format("%-12s GPA: %.1f Rank: #%d", "Navaneeth", 9.2, 1);
// "Navaneeth   GPA: 9.2 Rank: #1"
```

Format specifiers:
| Specifier | Meaning | Example |
|-----------|---------|---------|
| `%s` | String | `%s` → "hello" |
| `%d` | Integer | `%5d` → "   42" (right-padded to 5) |
| `%f` | Float | `%.2f` → "3.14" |
| `%-12s` | Left-align string in 12 chars | `"hello       "` |
| `%n` | Newline (platform-safe) | — |

### Complexity Summary

| Method | Loop of n | Single Call |
|--------|-----------|-------------|
| `+` operator | **O(n²)** | O(n) |
| `StringBuilder.append()` | **O(n)** | O(1) amortized |
| `String.join()` | O(n) | O(n) |
| `String.format()` | O(n) | O(n) |

---

## Section 6 — StringBuilder & StringBuffer

### Internal Model

StringBuilder works like `ArrayList` for characters:
- Internal `char[]` buffer, default capacity **16**
- When full: new capacity = `(old × 2) + 2`
- `append()` is O(1) amortized because resizes are rare and spread over many calls

```
Initial:  [H][e][l][l][o][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
           0  1  2  3  4                                 15
                              ↑ 5 chars, capacity 16

After append(" World"):
[H][e][l][l][o][ ][W][o][r][l][d][ ][ ][ ][ ][ ]
                              ↑ 11 chars, still fits in capacity 16
```

### Key Methods

```java
StringBuilder sb = new StringBuilder("Hello World");

sb.append("!!!");             // "Hello World!!!"  — O(1) amortized
sb.insert(5, ",");            // "Hello, World!!!" — O(n) shifts right
sb.delete(5, 6);              // "Hello World!!!"  — O(n) shifts left
sb.replace(6, 11, "Java");    // "Hello Java!!!"   — O(n)
sb.reverse();                 // "!!!avaJ olleH"   — O(n) in-place
sb.charAt(0);                 // 'H'               — O(1)
sb.toString();                // converts to String — O(n)
sb.length();                  // current length    — O(1)
```

### StringBuilder vs StringBuffer

```java
// Single-threaded (99% of cases)
StringBuilder sb = new StringBuilder();  // ✅ use this — no sync overhead

// Multi-threaded (concurrent access to same object)
StringBuffer  sbf = new StringBuffer();  // synchronized on every method call
```

In modern Java, if you need thread safety around string building, you usually use `StringBuilder` with explicit synchronization at a higher level, not `StringBuffer`.

### Complexity Table

| Method | Time | Space |
|--------|------|-------|
| `append(x)` | O(1) amortized | O(1) |
| `insert(i, x)` | O(n) | O(1) |
| `delete(s, e)` | O(n) | O(1) |
| `replace(s, e, x)` | O(n) | O(n) |
| `reverse()` | O(n) | O(1) — in-place |
| `charAt(i)` | O(1) | O(1) |
| `toString()` | O(n) | O(n) |
| `length()` | O(1) | O(1) |

---

## Section 7 — String Split & Join

### `split(regex)` — O(n)

```java
"Navaneeth,24,Chennai,Engineer".split(",");
// ["Navaneeth", "24", "Chennai", "Engineer"]

"2024-12-01 ERROR NullPointer: obj".split(" ", 3);
// ["2024-12-01", "ERROR", "NullPointer: obj"]  — max 3 parts

"one1two2three3".split("[0-9]");
// ["one", "two", "three"]  — split on any digit (regex)
```

The `limit` parameter in `split(regex, limit)` prevents splitting beyond `limit` parts — useful when the last field contains the delimiter (like log messages).

### Complexity

| Operation | Time | Space |
|-----------|------|-------|
| `split(regex)` | O(n) | O(n) — result array |
| `String.join()` | O(n) | O(n) — result string |

---

## Section 8 — Common String Algorithms

### 1. Palindrome Check — O(n) time, O(1) space

**Two-pointer technique:** place one pointer at the start, one at the end, move toward each other, compare.

```java
static boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) return false;
        left++;   // move inward
        right--;  // move inward
    }
    return true;
}
```

```
"racecar":
l=0, r=6: 'r' == 'r' ✓
l=1, r=5: 'a' == 'a' ✓
l=2, r=4: 'c' == 'c' ✓
l=3, r=3: left == right → stop → palindrome!
```

Only needs two integer variables — **O(1) space**.

---

### 2. Anagram Check — O(n) time, O(1) space

**Frequency array:** count occurrences of each of 26 letters. Two strings are anagrams if their frequency arrays are identical.

```java
static boolean isAnagram(String s1, String s2) {
    if (s1.length() != s2.length()) return false;

    int[] count = new int[26];          // fixed 26 — O(1) space regardless of input

    for (char c : s1.toCharArray()) count[c - 'a']++;  // increment for s1
    for (char c : s2.toCharArray()) count[c - 'a']--;  // decrement for s2

    for (int n : count) if (n != 0) return false;  // any mismatch?
    return true;
}
```

```
s1 = "listen", s2 = "silent"
count after processing s1: l=1, i=1, s=1, t=1, e=1, n=1
count after processing s2: l=0, i=0, s=0, t=0, e=0, n=0  ← all zero → anagram!
```

The 26-element array is **always exactly 26 elements**, regardless of input length → **O(1) space**.

---

### 3. Character Frequency — O(n) time, O(k) space

```java
Map<Character, Integer> map = new LinkedHashMap<>();
for (char c : s.toCharArray()) {
    map.put(c, map.getOrDefault(c, 0) + 1);
}
```

`k` = number of unique characters. For ASCII text, k ≤ 128. For just lowercase letters, k ≤ 26. Either way, effectively **O(1) space** for bounded character sets.

---

### 4. Reverse Words — O(n) time, O(n) space

```java
"Java is awesome"  →  "awesome is Java"
```

```java
String[] words = sentence.split(" ");          // ["Java", "is", "awesome"]
// Rebuild in reverse using StringBuilder
for (int i = words.length - 1; i >= 0; i--) {
    sb.append(words[i]);
    if (i > 0) sb.append(" ");
}
```

---

### 5. Longest Common Prefix — O(n × m) time, O(m) space

```java
["flower", "flow", "flight"]  →  "fl"
["interview", "interact", "interface"]  →  "inter"
```

Start with first string as prefix. Progressively shrink it until all strings start with it.

---

## The Three Rules of Java Strings

```java
// RULE 1: Always use .equals() for content comparison — NEVER ==
if (name == "Navaneeth")    { }  // ❌ compares addresses — unpredictable!
if (name.equals("Navaneeth")) { }  // ✅ compares content

// RULE 2: Always use StringBuilder for building strings in loops
String result = "";
for (...) result += word;        // ❌ O(n²) — creates new object each time
StringBuilder sb = new StringBuilder();
for (...) sb.append(word);       // ✅ O(n) — modifies internal buffer
String result = sb.toString();

// RULE 3: Remember every String method returns a NEW object
String s = "hello";
s.toUpperCase();     // ❌ result is discarded! s is still "hello"
s = s.toUpperCase(); // ✅ reassign to capture the new object
```

---

## Common Mistakes to Avoid

```java
// ❌ 1. == instead of .equals() for string comparison
String a = new String("hello");
String b = new String("hello");
if (a == b) { }         // false — different objects!
if (a.equals(b)) { }    // ✅ true

// ❌ 2. String + in loops
String csv = "";
for (String s : list) csv += s + ",";   // O(n²) — extremely slow for large lists
StringBuilder sb = new StringBuilder(); // ✅ O(n)
for (String s : list) sb.append(s).append(",");

// ❌ 3. Not trimming user input before processing
String username = "  Navaneeth  ";
if (username.equals("Navaneeth")) { }         // false — spaces included!
if (username.trim().equals("Navaneeth")) { }  // ✅ true

// ❌ 4. Off-by-one in substring
String s = "hello";
s.substring(1, 4);   // "ell" — end index is EXCLUSIVE (chars at 1, 2, 3)
s.substring(1, 5);   // "ello" — to get last char, use length() not length()-1

// ❌ 5. Using split() on special regex characters
String date = "12.01.2024";
date.split(".");     // ❌ '.' means "any character" in regex → splits everywhere!
date.split("\\.");   // ✅ escaped dot — splits on literal '.'

// ❌ 6. Ignoring case in comparisons
"Admin".equals("admin")             // false
"Admin".equalsIgnoreCase("admin")   // ✅ true — always use for user-supplied strings

// ❌ 7. NullPointerException on String methods
String s = null;
s.length();           // 💀 NullPointerException
"expected".equals(s) // ✅ put the known non-null string first
```

---

## Final Summary Table

| Concept | Time | Space | Key Rule |
|---------|------|-------|----------|
| String literal | O(n) | O(n) | Pool reuse |
| `charAt(i)` | O(1) | O(1) | Direct array access |
| `length()` | O(1) | O(1) | Stored as field |
| `equals()` | O(n) | O(1) | Use for content comparison |
| `==` | O(1) | O(1) | Reference only — avoid for content |
| `substring()` | O(n) | O(n) | Creates new String |
| `indexOf()` | O(n×m) | O(1) | Linear pattern search |
| `replace/All()` | O(n) | O(n) | New String created |
| `split()` | O(n) | O(n) | Returns new array |
| `String +` in loop | **O(n²)** | O(n) | ⚠️ Avoid |
| `StringBuilder.append()` | O(1) amrt | O(1) | Use in loops |
| `reverse()` | O(n) | O(1) | In-place |
| Palindrome | O(n) | O(1) | Two pointers |
| Anagram | O(n) | O(1) | Freq array[26] |
| Character frequency | O(n) | O(k) | HashMap |
