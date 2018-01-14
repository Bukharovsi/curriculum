/*
 * Находим вcе input'ы плана конкретной строки, пересчитываем строки
 * Обновляем total
 */
function totalHoursCalc(e) {
    //TODO: переделать для последнего
    var idAsArray = e.target.id.split('.')
    var template = idAsArray.slice(0, idAsArray.length -1).join('.')
    console.log(template)
    var id = "input[id*='" + template + "']"
    var totalHours = 0.0
    $(id).each(function (index, element) {
        if('totalHours' !== element.id.split('.')[2]) {
            totalHours += parseFloat(element.value)
        }
    })
    $("input[id='" + template + ".totalHours']").val(totalHours)
}
