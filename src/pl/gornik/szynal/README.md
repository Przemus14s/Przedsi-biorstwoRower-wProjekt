<section>
<h2>Projekt Przedsiębiorstwo Rowerów</h2>
</section>
<section>
<h4>Projekt przedsiębiorstwo rowerów pozwala nam wczuć się w rolę klienta/pracownika oraz samego menadżera 
takiego przedsiębiorstwa. Jest to wszechstronny i funkcjonalny program zawierający wiele rzeczy oraz możliwości a także
ma duży potencjał na rozbudowę. Projekt zawiera 14 klas, 1 wyjątek, 1 enuma oraz jedną klasę abstrakcyjną. Program zawiera 
dziedziczenie, walidację, sortowanie oraz "magazyn" w którym znajdują się części do rowerów. Jest również napisany czytelnie 
stosując najlepsze praktyki przejrzystości kodu. Każda klasa w nazwie ma tak naprawdę informację co dana klasa 
reprezentuje/zawiera. Klasy również są porozmieszczane w specjalnych paczkach aby wszystko było starannie posortowane. 
Projekt zrobiony na wersji SDK 23. Cały główny skrypt został przeniesiony do klasy App po to by klasa Main służyła tylko 
i wyłącznie do wywołania kodu. Po kliknięciu uruchom ukarzą się trzy opcję: Zarejestruj się, Zaloguj się oraz Wyjdź jest 
to menu logowania się do aplikacji. Gdy wybierzemy "Zarejestruj się" pokaże się opcja do wpisania nazwy użytkownika. Nazwa
może być dowolna. Następną rzeczą po wpisaniu nazwy będzie wpisanie hasła co ważne hasło posiada walidację minimum 6 liter, 
krótszych haseł program nie przepuści. Po wpisaniu odpowiedniego hasła będzie do wyboru rola "Menadżer", "Pracownik", "Klient". 
Po wybraniu program napisze że rejestracja została zakończona sukcesem. Teraz gdy jest utworzone konto można się zalogować. </h4>
</section>
<section>
<h2>Poniżej opiszę każdą rolę z osobna i jej funkcję.</h2>
<ul><h4>
<li>Menadżer - Pierwszą funkcją menadżera jest zarządzanie certyfikatami. Może on je dodać oraz wyświetlić. Certyfikaty nie są może czymś ważnym w całym programie ale dodają pewnego realizmu oraz klient może je wyświetlić. Aby dodać taki certyfikat trzeba wpisać tytuł oraz jego treść. Drugą opcją jest dodanie aktywności czyli np. jakiś zmian które planujemy wykonać możemy również wyświetlić te aktywności także nic się nie gubi w programie. Kolejną funkcją menadżera jest zarządzanie magazynem dość rozbudowana funkcja ponieważ gdy w nią wejdziemy ukażą się specyficzne dla tej funkcji zadania możemy dodać część do magazynu (tylko specjalne części które są zadeklarowane w klasie Warehouse na dole w dodatkowych informacjach je umieszczę ponieważ program żadnych innych części nie przyjmie z powodu walidacji). Możemy również wyświetlić części które mamy w magazynie oraz je usunąć. Następną funkcją menadżera jest wyświetlenie zrealizowanych zamówień gdy jesteśmy pracownikiem to możemy realizować zamówienia (jeżeli są części w magazynie) i tutaj będą one wypisywane. Przedostatnią już funkcją dla menadżera jest sprawdzenie pracowników. Funkcja ta pokazuje nam co robią nasi pracownicy (szanse 50/50 albo pracuje albo nie). I ostatnią funkcją którą posiada każda rola jest zmiana hasła jako dodatek.<br>
<br></li>
<li>
Pracownik - Pracownik ma trochę mniej funkcji niż menadżer. Jego pierwszą funkcją identycznie taką samą jak u menadżera jest zarządzanie magazynem (tak samo trzeba wpisać specyficzne części zadeklarowane w klasie Warehouse). Jego kolejną funkcją jest wyświetlenie zamówień czyli wszystkie zamówienia które zostały złożone przez klientów. Następną funkcją jest realizuj zamówienie żeby takie zrealizować musi być pierw wysłane przez klienta i potem wybieramy numerem id to zamówienie (zamówienia są posortowane względem kolejności alfabetycznej nazw klientów). Ważne jest aby podczas realizacji nie pomylić się we wpisywaniu nazwy produktu ponieważ program tego nie przyjmie. Oczywiście program tak samo nie przyjmie realizacji jeżeli nie będzie części w magazynie. Przedostatnią funkcją jest wyświetlenie zamówień które zostały zrealizowane. Ostatnią funckją roli pracownika jest zmiana hasła. <br>
<br></li>
<li>
Klient - Pierwszą funkcją dla klienta która jest nowa względem pozostałych ról jest złożenie zamówienia (W tym wypadku również trzeba stosować rzeczy z tabelki która będzie poniżej w dodatkowych informacjach). Podczas składania zamówienia klient zostanie zapytany o każdą część roweru i po wpisaniu każdej poprawnej zamówienie zostanie przyjęte. Kolejną opcją klienta jest wyświetlanie certyfikatów które są wpisywane przez menadżera. Klient może również wystawiać opinię od 1-5 oraz komentarz może również wyświetlić opinię jeżeli zostały zostawione przez innych klientów (każda opinia jest anonimowa). Ostatnią funkcją dla klienta jest zmiana hasła. <br>
</li></h4>
</ul>
</section>
<section><h2>Krótki opis najważniejszych klas (nie wszystkich)</h2><ul>
<li>
AssemblyLineEmployee - Reprezentuje pracownika na linii montażowej, posiada atrybut name oraz metodę do pobrania tej nazwy.
</li>
<li>
Authentication - Odpowiada za proces rejestracji użytkowników, logowanie i zarządzanie hasłami. Umożliwia przypisanie roli użytkownikowi (Menadżer, Pracownik, Klient).
</li>
<li>
Role - Enum reprezentujący różne role w systemie: Menadżer, Pracownik, Klient.
</li>
<li>
User - Reprezentuje użytkownika systemu. Ma nazwę użytkownika, hasło oraz rolę, umożliwia również zmianę hasła i roli.
</li>
<li>
App - Główna klasa aplikacji, obsługująca interakcje użytkownika. Umożliwia rejestrację, logowanie i zarządzanie opcjami dla różnych ról (Menadżer, Pracownik, Klient).
</li>
<li>
Main - Klasa uruchamiająca aplikację. Tworzy instancję App i wywołuje metodę run(), aby rozpocząć działanie programu.
</li>
<li>
Warehouse (Zakładając, że istnieje, ponieważ została użyta w klasie App) - Reprezentuje magazyn, zarządza częściami rowerów. Umożliwia dodawanie, usuwanie i listowanie dostępnych części.
</li>
<li>
CertificateManager - Zarządza certyfikatami w systemie. Umożliwia dodawanie nowych certyfikatów i ich wyświetlanie.
</li>
<li>
OrderManager - Odpowiada za zarządzanie zamówieniami. Umożliwia dodawanie nowych zamówień, wyświetlanie zrealizowanych oraz realizację zamówień.
</li>
<li>
FeedbackManager - Zarządza opiniami w systemie. Umożliwia dodawanie nowych opinii oraz ich wyświetlanie.
</li>
<li>
EmployeeTasks - Prawdopodobnie przechowuje zadania przypisane do pracowników i umożliwia sprawdzenie statusu pracowników.
</li>
<li>
SortedWarehouse (Zakładając, że to klasa, która implementuje magazyn) - Wersja magazynu, która może implementować posortowane przechowywanie części, ale szczegóły nie zostały dostarczone w kodzie.
</li>
<li>
ValidationException - Klasa wyjątku, która sygnalizuje błędy związane z walidacją (np. niepoprawne dane wejściowe).
</li>
</ul>
</section>
<section>
<h2>Dodatkowe informacje</h2>
<h4>Projekt zawiera w sobie dziedziczenie, zawiera klasę abstrakcyjną, enuma jak i wyjątek. Napisany przejrzyście z użyciem dobrego nazewnictwa klas. Parę metod jest nie użytych z myślą nad rozbudową programu (starałem się jak najmniej żeby takich klas było). Projekt zawiera walidację który jest umiejscowiony w wyjątku - WAŻNE te przedmioty można wpisywać do dodawania do magazynu i przy składaniu zamówienia </h4>
<h4><br>("Rama", "Rama aluminiowa", "Rama karbonowa", "Rama stalowa")
<br> ("Koła", "Koła szosowe", "Koła terenowe", "Koła miejskie")
<br>("Pedały", "Pedały platformowe", "Pedały zatrzaskowe", "Pedały składane")
 <br>("Kierownica prosta", "Kierownica gięta", "Kierownica typu baranek")
<br>("Dzwonek", "Dzwonek klasyczny", "Dzwonek elektroniczny", "Dzwonek retro")
<br>("Światła", "Światło LED przednie", "Światło tylne LED", "Światło halogenowe")</h4>
</section>