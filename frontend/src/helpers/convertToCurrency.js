export function convertToCurrency(money) {
    return new Intl.NumberFormat('nl-BE', {style: 'currency', currency: 'EUR'}).format(money);
}