<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h1>Rocks</h1>
                <table border="1">
                    <tr>
                        <th>name</th>
                        <th>origin</th>
                        <th>preciousness</th>
                        <th>color</th>

                        <th>transparency</th>
                        <th>gemCutting</th>
                        <th>value</th>
                    </tr>
                    <xsl:for-each select="gems/gem">
                        <tr>
                            <td>
                                <xsl:value-of select="name" />
                            </td>
                            <td>
                                <xsl:value-of select="origin" />
                            </td>
                            <td>
                                <xsl:value-of select="preciousness" />
                            </td>

                            <td>
                                <xsl:value-of select="visualParameters/color" />
                            </td>
                            <td>
                                <xsl:value-of select="visualParameters/transparency" />
                            </td>
                            <td>
                                <xsl:value-of select="visualParameters/gemCutting" />
                            </td>
                            <td>
                                <xsl:value-of select="value" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>

    </xsl:template>

</xsl:stylesheet>

