def main():
    words = []
    with open("words.txt", 'r', encoding='UTF-8') as file:
        words = file.readlines()
        
    filtered_words = []
    for word in words:
        if len(word.strip()) == 5:
            filtered_words.append(word.strip())
    
    with open("5_char_words.txt", "w", encoding='UTF-8') as file:
        for word in filtered_words:
            file.write(word.lower() + "\n")

if __name__ == "__main__":
    main()


