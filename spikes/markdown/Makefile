all:
	rm -rf ./html/*
	cp -R ./images ./html
	cp -R ./jsons ./html
	markdown-pp.py ./test.mdpp ./test.md
	generate-md --highlight mds-hljs --layout mixu-radar --input . --output ./html
