# SneakerStore

# Specs for SneakerShip
- Display a grid (column size of 2) of top 100 sneakers. The items should contain the image, price and name of the sneaker. This page should ideally have a search feature. On tapping the item, it should take you to the details page of that particular sneaker.
- The sneaker details page should contain its title, name, image, brand, year of release and price of the selected sneaker. This page should also have a “Add to Cart” button which adds the sneaker to a checkout cart page.
- The checkout page should contain all the sneakers added in a list displaying their image and price. This page should also show the total price of all the shoes in the end.
- Items should be removable from the cart. The checkout page should be opened by pressing the cart icon on the top right of the display page.

# Details:
- Uses a combination of Clean Architecture and MVVM ViewModels
- The main screens of the app has been distributed along multiple modules, one for each feature
- Dependency injection using Dagger Hilt
- Kotlin coroutines and flows
- Data is hard coded in a dummy data source

# Screens
- Home - SneakersList: This screen contains the grid of Sneakers.
- Sneaker details: Provides detailed information about the chosen sneaker
- Search: Facillitates Sneaker Search functionality
- Cart: The final page to buyout the sneakers

# Screenshots
| Home | Details | Search | Cart |
| -- | -- | -- | -- |
| <img src="https://github.com/vaibhavwani1509/SneakerStore/assets/138512798/abfb67f9-b34f-4e74-a3ca-4e3e784a6618" width="250"/> | <img src="https://github.com/vaibhavwani1509/SneakerStore/assets/138512798/14ec2dd6-f71c-497b-b0d9-c8401cef4413" width="250"/> | <img src="https://github.com/vaibhavwani1509/SneakerStore/assets/138512798/d0bb4c31-2bca-4b4c-9d7c-7d2ff74f21d7" width="250"/> | <img src="https://github.com/vaibhavwani1509/SneakerStore/assets/138512798/54b20de3-4cd0-4f49-8402-a8937b0b955f" width="250"/> |







