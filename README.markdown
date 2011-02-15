# enable vim ragel syntax highlighting

1. copy ragel.vim to *~/.vim/syntax*
2. create/modify file *~/.vim/filetype.vim* and add:

<pre>
augroup filetypedetect
au BufNewFile,BufRead *.rl setf ragel
augroup END
</pre>



