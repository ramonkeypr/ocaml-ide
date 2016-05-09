package org.ocaml.lang.parser

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.ocaml.lang.OcamlLanguage
import org.ocaml.lang.lexer.OcamlTypes
import org.ocaml.lang.parser.psi.OcamlNonTerminals
import org.ocaml.merlin.Merlin
import org.ocaml.merlin.MerlinLexer

/**
 * Created by sidharthkuruvila on 07/05/16.
 */

class OcamlParserDefinition : ParserDefinition {

    companion object {
        val FILE = IFileElementType(OcamlLanguage.INSTANCE);
    }

    override fun createParser(project: Project?): PsiParser? {
        return MerlinParser(Merlin.newInstance())
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile? {
        return OcamlPsiFileRoot(viewProvider)
    }

    override fun spaceExistanceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements? {
        return ParserDefinition.SpaceRequirements.MAY;
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.create(OcamlTypes.STRING)
    }

    override fun getFileNodeType(): IFileElementType? {
        return FILE
    }

    override fun createLexer(project: Project?): Lexer {
        return MerlinLexer(Merlin.newInstance())
    }

    override fun createElement(node: ASTNode): PsiElement {
        return OcamlNonTerminals.createElement(node)
    }

    override fun getCommentTokens(): TokenSet {
        return TokenSet.create(OcamlTypes.COMMENT)
    }

    override fun getWhitespaceTokens(): TokenSet {
        return TokenSet.create(TokenType.WHITE_SPACE)
    }

}