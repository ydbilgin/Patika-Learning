# MineSweeper Oyunu

Bu Java uygulaması, klasik **Mayın Tarlası** oyununu temsil eder. Oyunu oynamak için kullanıcıya bir oyun tahtası sunar ve kullanıcının mayınlar olmadan bütün hücreleri açmasını gerektirir.

## Nasıl Oynanır

1. Oyunu çalıştırdığınızda, oyun tahtası ve mayınların yerleri gösterilecektir.
2. Kullanıcıdan bir satır ve bir sütun numarası girmesi istenecektir.
3. Girilen koordinatı oynamak istediğiniz bölge olarak seçer ve bu bölgenin içeriğini görüntülersiniz.
   - Eğer bu bölgede bir mayın varsa, oyun kaybedilir ve oyun sona erer.
   - Eğer bu bölgede bir mayın yoksa, bu bölgenin etrafındaki mayınların sayısı görüntülenir. Eğer bu sayı 0 ise, komşu boş hücreler otomatik olarak açılır.
4. Oyunu kazanmak için bütün mayınsız hücreleri açmalısınız. Tüm mayınlı hücreleri açmamalısınız.