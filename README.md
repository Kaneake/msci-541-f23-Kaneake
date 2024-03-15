# msci-541-f23-hw5-Kaneake
msci-541-f23-hw5-Kaneake created by GitHub Classroom
#### Author: Kanykei Nurmambetova
#### Student ID#: 20872575

# How to build and run the code

IndexEngine program consumes documents as its input. - Metadata storage file, - (latimes.dat.gz) document to process file.

#### 1. Open command prompt/shell/terminal. Navigate to SearchEngine cloned repository on your device.

<i>
  The program accepts two line command arguments: a path to the latimes.gz file and a path to a directory
  where the documents and metadata will be stored. 
</i>

You will see a new txt file called addressBook.txt that stores metadata.
You will see a new txt file called output.txt that stores invertedIndex and lexicon.
You will see a new folder named after the second argument you gave with all latimes documents stored into correct date folders inside.

#### 2. Get query results:
```
javac HW5.java
java HW5
```

After the inverted index and lexicon are deserialized, you will be prompt to enter query.
After pressing enter, HW5 program will read it, tokenize it, and retrieve the top 10 results ranked by BM25 method as per HW5 instructions. You will also see a message reporting the execution time.

You can continue to enter more queries. The program will end when you type in "exit".
