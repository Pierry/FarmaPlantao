FarmaPlantao
========

O propósito é que a maioria das cidades consiga receber o app, então estamos trabalhando no formato colaborativo, 
logicamente apenas eu farei o deploy na Google Play Store, mas colocarei os créditos merecidos.

# Problema

Dado que o usuário necessite de uma farmácia, considerando não existir nenhuma aberta, apenas em regime de plantão, 
então o usuário deve ir até uma dessas farmácias para visualizar a lista dos plantões ou buscar em algum site local da cidade que mostra essa lista, dependendo da cidade, e só então ir até o local
indicado.

# Solução

Com o app, o usuário baixa a versão da sua cidade, e o app já mostra a farmácia que faz plantão no dia, mostrando um mapa pequeno para localização, possibilitando também o usuário criar uma rota, que será direcionada par ao Google Maps.

# Como adicionar sua cidade?

1.  Fork do app
2.  Adicione um arquivo semelhante ao src/main/assets/minhacidade.json com o nome da sua cidade,
contendo o nome da farmácia, endereço, dia aberto, latitude e longitude.
3.  Abra um Pull Request.

# Preview

![](https://raw.githubusercontent.com/Pierry/FarmaPlantao/master/app/src/main/art/hero.png)
