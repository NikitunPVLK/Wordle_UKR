from bs4 import BeautifulSoup
import requests

BASE_URL = "https://uk.wiktionary.org"

def main():
    page_url = "/w/index.php?title=Категорія:Українські_іменники&from=А"
    while True:
        response = requests.get(BASE_URL + page_url)
        root = BeautifulSoup(response.text, "html.parser")
        words_div = root.find_all('div', class_ = "mw-category-group")[-1]
        words_list_element = words_div.find('ul')
        with open("words.txt", "a", encoding='UTF-8') as file:
            file.write(words_list_element.text + "\n")

        main_div = root.find('div', id='mw-pages')
        next_page_element = main_div.find_all('a')[-1]
        if next_page_element.text != "наступна сторінка":
            return
        page_url = next_page_element.get('href')

if __name__ == "__main__":
    main()