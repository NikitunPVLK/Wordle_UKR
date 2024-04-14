file_name = "frequency-dict.txt"

def main():
    nouns = []
    with open(file_name, "r", encoding="UTF-8") as file:
        lines = file.readlines()
        for line in lines:
            line.isupper
            if line.find("ім.}") != -1:
                nouns.append(line)
    nouns_5_char = []
    for line in nouns:
        coma_index = line.find(",")
        word = line[1:coma_index]
        if len(word) == 5 and (not word[0].isupper()):
            nouns_5_char.append(word)
    words_count = len(nouns_5_char)
    level_size = int(words_count / 3)
    easy_level = nouns_5_char[:level_size + 1]
    normal_level = nouns_5_char[level_size + 1:level_size*2 + 2]
    hard_level = nouns_5_char[level_size*2 + 2:level_size*3 + 2]

    write_txt("easy.txt", easy_level)
    write_txt("normal.txt", normal_level)
    write_txt("hard.txt", hard_level)
    write_txt("mixed.txt", nouns_5_char)

    write_xml("easy_words.xml", easy_level, "easy_words")
    write_xml("normal_words.xml", normal_level, "normal_words")
    write_xml("hard_words.xml", hard_level, "hard_words")
    write_xml("mixed_words.xml", nouns_5_char, "mixed_words")

def write_txt(filename, words):
    with open(filename, "w", encoding="UTF-8") as file:
        for word in words:
            file.write(word + "\n")

def write_xml(filename, words, array_name):
    with open(filename, "w", encoding="UTF-8") as file:
        file.write('<?xml version="1.0" encoding="utf-8"?>\n')
        file.write('<resources>\n')
        file.write(f'\t<string-array name="{array_name}">\n')
        for word in words:
            file.write('\t\t<item>' + word + "</item>\n")
        file.write('\t</string-array>\n')
        file.write('</resources>\n')

if __name__ == "__main__":
    main()