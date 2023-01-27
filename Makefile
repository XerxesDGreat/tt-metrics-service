all:
	ag asyncapi.yaml @asyncapi/java-template -o output -p server=demo