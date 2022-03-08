Set objShell = CreateObject("WScript.Shell")
Set args =  Wscript.Arguments
filePath = args.Item(0)
Set objExcel = CreateObject("Excel.Application")
Set objWorkbook = objExcel.Workbooks.Open(filePath, , False)
objExcel.Application.Visible = False
objExcel.Application.Run "RandomizeDealData"
WScript.Sleep 5000
'objExcel.Cells(28, 2).Value = "ExcelTest"
objExcel.ActiveWorkbook.save
'objExcel.Application.DisplayAlerts = False
objExcel.ActiveWorkbook.Close
objExcel.Application.Quit
Set objWorkbook = Nothing
Set objExcel = Nothing


