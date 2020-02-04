/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.icure.services.external.rest.v1.dto.gui;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import org.taktik.icure.services.external.rest.handlers.JacksonEditorDeserializer;
import org.taktik.icure.services.external.rest.handlers.JsonDiscriminator;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.ActionButton;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.Audiometry;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.CheckBoxEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.DashboardEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.DateTimeEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.HealthcarePartyEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.IntegerSliderEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.Label;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.MeasureEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.MedicationEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.MedicationTableEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.NumberEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.PopupMenuEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.SchemaEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.StringEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.StringTableEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.StyledStringEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.SubFormEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.TokenFieldEditor;
import org.taktik.icure.services.external.rest.v1.dto.gui.editor.TypeValueStringEditor;

/**
 * Created by aduchate on 19/11/13, 15:28
 */
@XStreamAlias("Editor")
@JsonDiscriminator("key")
@JsonDeserialize(using = JacksonEditorDeserializer.class)
public abstract class Editor implements Serializable {
    @XStreamAsAttribute
    private Double left;
    @XStreamAsAttribute
    private Double top;
    @XStreamAsAttribute
    private Double width;
    @XStreamAsAttribute
    private Double height;

	@XStreamAsAttribute
	boolean multiline;

    @XStreamAsAttribute
	LabelPosition labelPosition;

	@XStreamAsAttribute
	private boolean readOnly;

	public Editor() {
	}

	public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public Double getTop() {
        return top;
    }

    public void setTop(Double top) {
        this.top = top;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public LabelPosition getLabelPosition() {
        return labelPosition;
    }

    public void setLabelPosition(LabelPosition labelPosition) {
        this.labelPosition = labelPosition;
    }

	public boolean isMultiline() {
		return multiline;
	}

	public void setMultiline(boolean multiline) {
		this.multiline = multiline;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
}
