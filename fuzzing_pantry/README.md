openssl genpkey -out private.key -algorithm RSA -pkeyopt rsa_keygen_bits:2048
openssl rsa -pubout -in private.key -out public_key.pub

username = fuzzer
password = fuzz