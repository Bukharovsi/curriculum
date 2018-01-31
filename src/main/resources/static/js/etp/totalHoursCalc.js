/*
 * Находим вcе input'ы плана конкретной строки, пересчитываем строки
 * Обновляем total
 */


// function totalHoursCalc(e) {
//
//     var idAsArray = e.target.id.split('.')
//
//     calcTotalRow(idAsArray.slice(0, idAsArray.length -1).join('.'))
//     calcTotalColumn(idAsArray[2])
//
//     // Пример id html элемента: emaModules[0].plan.lectures
//     // var template = idAsArray.slice(0, idAsArray.length -1).join('.')
//     // var id = "input[id*='" + template + "']"
//     // var totalHours = 0.0
//     // $(id).each(function (index, element) {
//     //     if(!~element.id.indexOf('totalHours')) {
//     //         totalHours += parseFloat(element.value)
//     //     }
//     // })
//     // $("input[id='" + template + ".totalHours']").val(totalHours)
// }
//
// //TODO: маску отдельно для int и float значений
// // Если десятичное то всегда чтобы было число после запятой
// function calcTotalRow(rowName) {
//     const lernerCount = /lernerCount/,
//         groupCount = /groupCount/,
//         conditionalPagesCount = /conditionalPagesCount/,
//         totalHours = /totalHours/
//
//     var rowSelector = getSearchSelectorTemplate(rowName)
//
//     var total = 0.0
//     $(rowSelector).each(function (i, e) {
//         if(!(lernerCount.test(e.name) || groupCount.test(e.name) || conditionalPagesCount.test(e.name) || totalHours.test(e.name))) {
//             total += parseFloat(e.value)
//         }
//     })
//
//     $("input[id='" + rowName + ".totalHours']").val(total)
//     calcTotalColumn('totalHours')
// }
//
// function calcTotalColumn(colName) {
//     var colSelector = getSearchSelectorTemplate(colName)
//
//     var emaTotal = 0.0,
//         omaTotal = 0.0,
//         eaTotal = 0.0
//
//     $(colSelector).each(function (i, e) {
//         var emaModule = /emaModules/,
//             omaModule = /omaModules/,
//             eaModule = /eaModules/
//
//         switch (true) {
//             case emaModule.test(e.name):
//                 emaTotal += parseFloat(e.value)
//                 break
//             case omaModule.test(e.name):
//                 omaTotal += parseFloat(e.value)
//                 break
//             case eaModule.test(e.name):
//                 eaTotal += parseFloat(e.value)
//                 break
//             default:
//                 console.log("Unknown input name")
//         }
//     })
//
//     $('#emaModuleTotalRow\\.' + colName).val(emaTotal)
//     $('#omaModuleTotalRow\\.' + colName).val(omaTotal)
//     $('#eaModuleTotalRow\\.' + colName).val(eaTotal)
//     $('#etpTotalRow\\.' + colName).val((emaTotal + omaTotal + eaTotal))
// }
//
// function getSearchSelectorTemplate(partOfId) {
//     return "input[id*='" + partOfId + "']"
// }
