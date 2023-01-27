all:
	ag asyncapi.yaml @asyncapi/python-paho-template -o output -p server=demo