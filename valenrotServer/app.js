const http = require("http");
const request = require('request');
const pdfMake = require("pdfmake");

http.createServer(async function(req, res){
     
    res.setHeader("Content-Type", "application/json; charset=utf-8;");
     
    if(req.url === "/home" || req.url === "/"){
        res.write("<h2>Home</h2>");
    }
    else if(req.url == "/api/upload"){
        let body = '';
        req.on('data', (chunk) => {
            body += chunk;
        });
        req.on('end', () => {
            console.log(body);
            generatePdf(body);
            res.write('OK'); 
            res.end(); 
        });
    }
    else if(req.url == "/api/names"){

        var body = await doRequest('https://randomall.ru/api/general/fantasy_name')
        console.log(body.split("<br>"));
        res.write(JSON.stringify(body.split("<br>")));
    }
    else{
        res.write("<h2>Not found</h2>");
    }
    res.end();
}).listen(3000);


function doRequest(url) {
    return new Promise(function (resolve, reject) {
      request.post(url, function (error, res, body) {
        if (!error && res.statusCode === 200) {
          resolve(body);
        } else {
          reject(error);
        }
      });
    });
}

function generatePdf(data) {
    var fonts = {
        Roboto: {
          normal: 'fonts/Roboto-Regular.ttf',
          bold: 'fonts/Roboto-Medium.ttf',
          italics: 'fonts/Roboto-Italic.ttf',
          bolditalics: 'fonts/Roboto-MediumItalic.ttf'
        }
      };
      
      var PdfPrinter = require('pdfmake');
      var printer = new PdfPrinter(fonts);
      var fs = require('fs');
      
      var docDefinition = {
        content: data
      };
      
      var options = {
        // ...
      }
      
      var pdfDoc = printer.createPdfKitDocument(docDefinition, options);
      pdfDoc.pipe(fs.createWriteStream('document.pdf'));
      pdfDoc.end();
}