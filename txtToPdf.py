from fpdf import FPDF
import webbrowser as wb
import os
pdf = FPDF()

for root, dirs, files in os.walk(".", topdown=False):
    for name in files:
        if name.endswith('.txt'):
            pdf.add_page()
            pdf.set_font('Arial', size=15)
            f = open(name, "r")
            for x in f:
                pdf.cell(50, 5, txt=x, ln=1, align='C')
            pdf.output("receipt.pdf")
            os.remove(name)
            wb.open_new(
                r'C:\02_Škola\Dánsko\Škola\Programovanie\HotelPlaza\receipt.pdf')
