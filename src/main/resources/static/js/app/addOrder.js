let totalPrice;
let backLocation = () => {
    let coffeeUid = new URLSearchParams(location.search).get("coffeeUid");
    window.location.href="/coffees/coffeedetail?uid="+coffeeUid;
}