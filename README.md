#### Asciidoctorの使い方メモ
##### 主なコマンド
* html,cssを作成する。

    ```
    asciidoctor --backend html5 -o index.html todo.adoc
    ```
* pdfを作成する。

    ```
    git clone https://github.com/asciidoctor/asciidoctor-fopub
    ```
    ```
    asciidoctor -b docbook -d article -a data-uri! todo.adoc
    ```
    ```
    ./fopub index.xml \
      -param title.font.family VL-PGothic-Regular \
      -param body.font.family VL-PGothic-Regular \
      -param sans.font.family VL-PGothic-Regular \
      -param monospace.font.family VL-PGothic-Regular \
      -param symbol.font.family VL-PGothic-Regular
      ```
