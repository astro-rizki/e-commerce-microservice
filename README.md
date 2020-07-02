# e-commerce-microservice

based on work of https://github.com/venkataravuri/e-commerce-microservices-sample

What do I change :
 - adding Eureka server Dependency
 - ganti nama yg discovery service, supaya buildnya sesuai
 - menerapkan konsep CQRS (walau sekarang masih ada get di product-service :3)
 - gaada lagi sih yg dirubah

## Notes
Proyek lama dibuild pakai gradle 3.1. target kita pake 6.5.,  jadi jangan dirubah2 versi gradlenya, kecuali u keluarin servicenya terus bikin lagi diluar wrappernya. Trust me, aku nyobain ngedit product service yg lama, pas gradlenya diupdate, jadi error semua servicenya.
Why changing gradle?
Because new version supporting SB (spring-boot 2.x) which is, support new version of mongo driver, and support asynchronous.. GO SISTER GO! 

## How To 
Aku bikin product service baru ya, tapi ga ngereplace fitur product yang lama. Jadinya ada 2 fitur product service. Ngejalaninnya gimana ki? 

1. gradlew build di folder utama (kaya kemarin)
2. pindah ke folder product-service (yg baru), trus gradlew build di situ
3. pindah ke folder product-search-service, trus gradlew build di situ
4. berestu docker-compose kayak biasa, (build, trus up)
5. aksesnya di port 9007 (product-service baru) dan di port 9008 (product-search-service) yak

cara CRUD nya gimana, coba diliat di source nya ya, terus kalo butuh id buat dijadiin param, coba pas udh di docker run (docker compose up), buka container mongodb nya, terus buka cli, masukin ini (buat cek isi mongonya)

\# mongo 

\> use products

\> db.products.find().pretty()

buat liat id di product-search-service tinggal buka aja localhost:9008/products/list, nanti id nya ada di situ, gan.

tembak2annya pake postman aja biar gampang, btw nanti fungsi get bakal dihapus, kita coba pattern CQRS ya.. 
(link belajar CQRS : https://www.youtube.com/watch?v=eei78T5Nz-U)

Feel free to explore ^^

Update 2 Juli 2020 :
- ada service baru, product-search
- tambahan how to untuk service baru, product-search